# coding=UTF-8
'''
@Author: Fantasy
@Date: 2020-01-13 12:37:20
@LastEditors  : Fantasy
@LastEditTime : 2020-01-22 17:04:29
@Descripttion:
@Email: 776474961@qq.com
'''
import os
import re

class DBAnalyze(object):
    def __init__(self, fileName):
        self.fileName = fileName
        self.dbName = ""
        self.createTable = False
        self.tables = []
        '''格式
            tables = [
                        {'tableName':'test',
                        'columns':[{'column_name':'user_name','columnName':'userName','ColumnName':'UserName','columnType':'','comment':''}]
                        },
                    ]
        '''
        self.line = 0
        self.startAnalyze()

    def formatCamelCaseBySmall(self, name):
        '''格式化为小驼峰'''
        name = name[0].lower() + name[1:]
        items = name.split('_')
        camelName = []
        camelName.append(items[0])
        [camelName.append(item.capitalize()) for item in items[1:]]
        return "".join(camelName)

    def formatCamelCaseByBig(self, name):
        '''格式化为大驼峰'''
        camelName = []
        items = name.split('_')
        [camelName.append(item.capitalize()) for item in items]
        return "".join(camelName)

    def formatLine(self, line):
        line = line.strip()
        line = line.lower()
        line = line.replace('"', "'")
        return line

    def checkKeyword(self, line, keys):
        '''检查关键字'''
        for key in keys:
            if not re.findall(key, line):
                return False
        return True

    def isWasteLine(self, line):
        if len(re.findall(r'`\S+?`', line)) > 1 or self.checkKeyword(line, "primary key".split()):
            return True
        return False

    def isCreateDB(self, line):
        '''是否为创建数据库'''
        keys = "create database".split()
        if self.checkKeyword(line, keys):
            dbname = re.findall(r'`(\w+?)`', line)[0]
            return dbname
        return False

    def isCreateTable(self, line):
        '''是否为创建表'''
        keys = "create table".split()
        if self.checkKeyword(line, keys):
            tableName = re.findall(r'`(\w+?)`', line)[0]
            return tableName
        return False

    def isFinishTable(self, line):
        '''是否建创建表结束'''
        keys = ";".split()
        if self.checkKeyword(line, keys):
            return True
        else:
            return False

    def isCreateColumn(self, line):
        '''提取列'''
        items = re.findall(r'`(\w+?)`', line)
        column = {}
        if items:
            column['column_name'] = items[0]
            column['columnName'] = self.formatCamelCaseBySmall(items[0])
            column['ColumnName'] = self.formatCamelCaseByBig(items[0])
            column['columnType'] = re.sub(r'\(.+\)', "", line.split()[1])
            if self.hasComment(line):
                column['comment'] = self.hasComment(line)
        return column

    def hasComment(self, line):
        comment = ""
        if re.findall(r'\scomment\s', line):
            comment = line.split('comment')[-1]
            if re.findall(r"'(.+)'", comment):
                comment = re.findall(r"'(.+)'", comment)[0]
            return comment
        return False

    def startAnalyze(self):
        with open(self.fileName, 'r', encoding='utf-8', errors='ignore') as f:
            for line in f.readlines():
                line = self.formatLine(line)
                if self.isWasteLine(line):
                    pass
                elif self.isCreateDB(line):
                    self.dbName = self.isCreateDB(line)
                    # print("create database :"+self.isCreateDB(line))
                elif self.isCreateTable(line):
                    table = {}
                    tableName = self.isCreateTable(line)
                    table['table_name'] = tableName
                    table['tableName'] = self.formatCamelCaseBySmall(tableName)
                    table['TableName'] = self.formatCamelCaseByBig(tableName)
                    table['columns'] = []
                    self.createTable = True
                    # print("create table :"+self.isCreateTable(line))
                elif self.createTable and self.isFinishTable(line):
                    # print("create table finish :"+self.tableName)
                    if self.hasComment(line):
                        table['tableComment'] = self.hasComment(line)
                    self.tables.append(table)
                    self.createTable = False
                elif self.createTable and self.isCreateColumn(line):
                    table['columns'].append(self.isCreateColumn(line))
                    # print(self.isDefineColumn(line))


if __name__ == "__main__":
    db = DBAnalyze('D:\\GitHub\\DBToCode\\sideline.sql')
    print(db.tables)
