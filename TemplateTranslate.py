# coding=UTF-8
'''
@Author: Fantasy
@Date: 2020-01-13 14:00:32
@LastEditors  : Fantasy
@LastEditTime : 2020-02-12 15:38:12
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
        self.currentTable = {}
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
        # 递归扫描模版目录
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
        # 首字母是否大写
        if name[0].istitle():
            return True
        else:
            return False

    def getCamelCaseName(self, name):
        # 根据首字母获取相应驼峰命名

        # 首字母大写,则大驼峰
        if self.checkCamelCase(name):
            return self.DBA.formatCamelCaseByBig(name)
        else:
            return self.DBA.formatCamelCaseBySmall(name)

    def formatPath(self, path):
        # 格式化路径
        path = re.sub(r'\\', os.sep, path)
        path = re.sub(r'/', os.sep, path)
        return path

    def createFile(self, filePath):
        # 递归创建文件

        path = filePath[0:filePath.rfind(os.sep)]
        if not os.path.isdir(path):  # 无文件夹时创建
            os.makedirs(path)
        if not os.path.isfile(filePath):  # 无文件时创建
            fd = open(filePath, mode="w", encoding="utf-8")
            fd.close()
        else:
            pass

    def getConfValue(self, index, conf):
        # 从配置文件取值

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
        # 识别并翻译行
        # 找到所有变量
        items = re.findall(r'\$\${(.*?)}', line)
        for i in range(len(items)):
            items[i] = items[i].strip()
            
            if items[i] in self.currentTable.keys():
                items[i] = self.currentTable[items[i]]
            # 变量不匹配则尝试配置文件
            elif items[i] not in data.keys():
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
        # 翻译循环语句
        
        result = []
        for column in table['columns']:
            for content in self.cycleContent:
                result.append(self.lineTranslate(content, column))
        self.cycleContent = []
        return "".join(result)

    def syntax(self, inputFile, line, table):
        # 模版语法分析

        # 若是循环
        if re.findall(r'@{', line):
            self.isCycle = True
            while True:
                line = inputFile.readline()

                # 若不是循环结束
                if not re.findall(r'}@', line):
                    self.cycleContent.append(line)
                # 若是循环结束
                else:
                    self.isCycle = False
                    return self.dealCycle(table)
        # 若不是循环而是取变量
        elif re.findall(r'\$\${.*}', line):
            return self.lineTranslate(line, table)
        # 不是循环也不是变量,直接返回字符串
        else:
            return line

    def translate(self):
        # 入口函数
        self.scanDir(self.templateDir)

        # 遍历所有模版文件
        for template in self.templates:
            inputFile = open(
                template['filePath'], 'r+', encoding='utf8', errors="ignore")
            outputPath = os.path.join(
                self.outputDir, "." + template['filePath'].replace(self.templateDir, ""))
            outputPath = os.path.abspath(outputPath)
            # 输入文件与表无关(模版无关文件),直接复制
            if not re.findall(r'\$\${.*}', template['filePath']):
                self.createFile(outputPath)
                outputFile = open(outputPath, 'w+', encoding="utf8")
                while True:
                    line = inputFile.readline()
                    outputFile.write(self.syntax(inputFile, line, self.conf))
                    if not line:
                        break
            # 输入文件需要按表迭代生成
            else:
                for table in self.DBA.tables:
                    self.currentTable = table
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

    def getMybatisSql(self):
        # 按表转化为mybatis 多表关联所需要的字段 table_name.column_name -> table_name_column_name
        dbInfo = self.DBA
        targetStr = []
        for table in dbInfo.tables:
            tableName = table['table_name']
            targetStrLine = ""
            for column in table['columns']:
                targetStrLine = targetStrLine + tableName + '.' + column['column_name'] + ' ' + tableName + '_' + column['column_name'] + ','
            targetStr.append(targetStrLine)
        return targetStr

    def getMybatisResultMap(self):
        # 按表转化为mybatis 多表关联所需要的结果map: <result column="c_province" property="province" />
        dbInfo = self.DBA
        targetStr = []
        for table in dbInfo.tables:
            tableName = table['table_name']
            tableStr = []
            tableStrLineTemplate = '<result column="{}" property="{}" />'
            for column in table['columns']:
                tableStrLine = tableStrLineTemplate.format(tableName+"_"+column['column_name'], column['columnName'])
                tableStr.append(tableStrLine)
            targetStr.append(tableStr)
        return targetStr


if __name__ == "__main__":
    dbInfo = DBAnalyze('C:\\Users\\Fantasy\\Desktop\\tutor.sql')
    tt = TemplateTranslate("./template/java/springboot", "./out", dbInfo)
    # tt.translate()
    mybatisSql = tt.getMybatisSql()
    mybatisResultMap = tt.getMybatisResultMap()
    for tableSql in mybatisSql:
        print(tableSql)

    for tableMap in mybatisResultMap:
        for columnMap in tableMap:
            print(columnMap)
        print()