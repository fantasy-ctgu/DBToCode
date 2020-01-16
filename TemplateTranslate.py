# coding=UTF-8
'''
@Author: Fantasy
@Date: 2020-01-13 14:00:32
@LastEditors  : Fantasy
@LastEditTime : 2020-01-16 10:48:05
@Descripttion: 
@Email: 776474961@qq.com
'''

import os
import re
import json
from DBAnalyze import DBAnalyze


class TemplateTranslate(object):
    def __init__(self, templateDir, outputDir, dbInfo):
        self.DBA = dbInfo
        self.templateDir = os.path.abspath(templateDir)
        fp = open(os.path.join(self.templateDir, "conf.json"),
                  "r+", encoding="utf-8")
        self.conf = json.load(fp)
        fp.close()
        self.outputDir = os.path.abspath(outputDir)
        self.templates = []
        self.isCycle = False
        self.cycleContent = []

    def scanDir(self, templateDir):
        objs = os.listdir(templateDir)
        for obj in objs:
            file = os.path.join(templateDir, obj)
            if os.path.isdir(file):
                self.scanDir(file)
            else:
                template = {}
                template['fileName'] = obj
                template['filePath'] = file
                self.templates.append(template)

    def checkCamelCase(self, name):
        if name[0].istitle():
            return True
        else:
            return False

    def getCamelCaseName(self, name):
        if self.checkCamelCase(name):
            return self.DBA.formatCamelCaseByBig(name)
        else:
            return self.DBA.formatCamelCaseBySmall(name)

    def formatPath(self, path):
        path = re.sub(r'\\', os.sep, path)
        path = re.sub(r'/', os.sep, path)
        return path

    def createFile(self, filePath):
        path = filePath[0:filePath.rfind(os.sep)]
        if not os.path.isdir(path):  # 无文件夹时创建
            os.makedirs(path)
        if not os.path.isfile(filePath):  # 无文件时创建
            fd = open(filePath, mode="w", encoding="utf-8")
            fd.close()
        else:
            pass

    def getConfValue(self, index, conf):
        if isinstance(index, str):
            index = index.split(".")
        try:
            if len(index) >= 1 and index[0] in conf.keys():
                if len(index) == 1:
                    return conf[index[0]]
                else:
                    return self.getConfValue(index[1:], conf[index[0]])
            else:
                return ""
        except BaseException:
            return ""

    def lineTranslate(self, line, data):
        items = re.findall(r'\$\${(.*?)}', line)
        for i in range(len(items)):
            items[i] = items[i].strip()
            if items[i] not in data.keys():
                items[i] = self.getConfValue(items[i], self.conf)
            elif items[i] == 'columnType' and self.getConfValue("typeTranslate.translate", self.conf):
                newType = self.getConfValue(
                    "typeTranslate.typeMap." + data[items[i]], self.conf)
                items[i] = newType if len(newType) > 0 else data[items[i]]
            else:
                items[i] = data[items[i]]
        items.append("")
        str = re.split(r'\$\${.*?}', line)
        result = [rv for r in zip(str, items) for rv in r]
        return "".join(result)

    def dealCycle(self, table):
        result = []
        for column in table['columns']:
            for content in self.cycleContent:
                result.append(self.lineTranslate(content, column))
        self.cycleContent = []
        return "".join(result)

    def syntax(self, inputFile, line, table):
        if re.findall(r'@{', line):
            self.isCycle = True
            while True:
                line = inputFile.readline()
                if not re.findall(r'}@', line):
                    self.cycleContent.append(line)
                else:
                    self.isCycle = False
                    return self.dealCycle(table)
        elif re.findall(r'\$\${.*}', line):
            return self.lineTranslate(line, table)
        else:
            return line

    def translate(self):
        self.scanDir(self.templateDir)
        for template in self.templates:
            inputFile = open(
                template['filePath'], 'r+', encoding='utf8', errors="ignore")
            outputPath = os.path.join(
                self.outputDir, "." + template['filePath'].replace(self.templateDir, ""))
            outputPath = os.path.abspath(outputPath)

            if not re.findall(r'\$\${.*}', template['filePath']):
                self.createFile(outputPath)
                outputFile = open(outputPath, 'w+', encoding="utf8")
                while True:
                    line = inputFile.readline()
                    outputFile.write(self.syntax(inputFile, line, {}))
                    if not line:
                        break
            else:
                for table in self.DBA.tables:
                    inputFile.seek(0, 0)
                    outputFileName = self.lineTranslate(outputPath, table)
                    self.createFile(outputFileName)
                    outputFile = open(outputFileName, 'w+', encoding="utf8")
                    while True:
                        line = inputFile.readline()
                        outputFile.write(self.syntax(inputFile, line, table))
                        if not line:
                            break
            inputFile.close()
            outputFile.close()


if __name__ == "__main__":
    dbInfo = DBAnalyze('./testdb.sql')
    tt = TemplateTranslate("./template/java/springboot", "./out", dbInfo)
    tt.translate()
    # tt = TemplateTranslate("./template/test", "./out", dbInfo)
