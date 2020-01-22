# coding=UTF-8
'''
@Author: Fantasy
@Date: 2020-01-15 14:21:57
@LastEditors  : Fantasy
@LastEditTime : 2020-01-22 15:34:03
@Descripttion: 
@Email: 776474961@qq.com
'''
import json
import argparse
from TemplateTranslate import TemplateTranslate
from DBAnalyze import DBAnalyze

if __name__ == "__main__":
    # parser = argparse.ArgumentParser(description='The database code to orther code.')
    # parser.add_argument('-db', required=True, help='The database file path.')
    # parser.add_argument('-intem',metavar='input_template', required=True, help='The translate code template.')
    # parser.add_argument('-outem',metavar='output_template', required=True, help='The general code output path.')
    # args = parser.parse_args()
    # print(args)
    dbInfo = DBAnalyze('sideline.sql')
    print(dbInfo.tables)
    # tt = TemplateTranslate("./template/java/springboot", "./out", dbInfo)
    # tt.translate()
    # fp = open("./template/java/springboot/conf.json", "r+", encoding="utf-8")
    # conf = json.load(fp)
    # print(conf['typeTranslate.typeMap'])