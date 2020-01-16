# coding=UTF-8
'''
@Author: Fantasy
@Date: 2020-01-13 12:37:20
@LastEditors  : Fantasy
@LastEditTime : 2020-01-16 10:59:04
@Descripttion: 
@Email: 776474961@qq.com
'''
import os
import re


class DBAnalyze(object):
    def __init__(self, fileName):
        self.fileName = fileName
        self.dbName = ""
        self.tables = []
        '''格式
            [
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

    def isCreateDB(self, line):
        '''是否为创建数据库'''
        if(re.findall(r'(database)', line)):
            if(re.findall(r'`', line)):
                dbname = re.findall(r'`(\w+)`', line)[0]
            elif re.findall(r'(exists)', line):
                dbname = re.findall(r'(exists)\s(\w+)', line)[0][-1]
            else:
                dbname = re.findall(r'(database)\s(\w+)', line)[0][-1]
            return dbname
        return False

    def isCreateTable(self, line):
        '''是否为创建表'''
        if(re.findall(r'table', line)):
            if(re.findall(r'`', line)):
                tableName = re.findall(r'`(\w+)`', line)[0]
            elif re.findall(r'exists', line):
                tableName = re.findall(r'(exists)\s(\w+)', line)[0][-1]
            else:
                tableName = re.findall(r'(table)\s(\w+)', line)[0][-1]
            self.tableName = tableName
            return tableName
        return False

    def isFinishTable(self, line):
        '''是否建创建表结束'''
        if re.findall(r';', line):
            return True
        else:
            return False

    def isDefineColumn(self, line):
        '''提取列'''
        items = line.split()
        column = {}
        if len(items) >= 2:
            column['column_name'] = items[0]
            column['columnName'] = self.formatCamelCaseBySmall(items[0])
            column['ColumnName'] = self.formatCamelCaseByBig(items[0])
            column['columnType'] = re.sub(r'\(\d*\)', "", items[1])
            if re.findall(r'\scomment\s', line):
                str = line.split('comment')[-1].split()[-1]
                if re.findall(r'"(.+)"', str):
                    str = re.findall(r'"(.+)"', str)[0]
                elif re.findall(r"'(.+)'", str):
                    str = re.findall(r"'(.+)'", str)[0]
                column['comment'] = str
        return column

    def startAnalyze(self):
        with open(self.fileName, 'r', encoding='utf-8', errors='ignore') as f:
            isCreateTable = False
            for line in f.readlines():
                line = line.strip()
                # print(self.line)
                line = line.lower()
                if self.isCreateDB(line):
                    self.dbName = self.isCreateDB(line)
                    # print("create database :"+self.isCreateDB(line))
                elif self.isCreateTable(line):
                    table = {}
                    tableName = self.isCreateTable(line)
                    table['table_name'] = tableName
                    table['tableName'] = self.formatCamelCaseBySmall(tableName)
                    table['TableName'] = self.formatCamelCaseByBig(tableName)
                    table['columns'] = []
                    isCreateTable = True
                    # print("create table :"+self.isCreateTable(line))
                elif isCreateTable and self.isFinishTable(line):
                    # print("create table finish :"+self.tableName)
                    self.tables.append(table)
                    isCreateTable = False
                elif isCreateTable and self.isDefineColumn(line):
                    table['columns'].append(self.isDefineColumn(line))
                    # print(self.isDefineColumn(line))


if __name__ == "__main__":
    db = DBAnalyze('testdb.sql')
    print(db.tables)
