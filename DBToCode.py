# coding=UTF-8
'''
@Author: Fantasy
@Date: 2020-01-15 14:21:57
@LastEditors: Fantasy
@LastEditTime: 2020-04-02 22:29:42
@Descripttion: 
@Email: 776474961@qq.com
'''
import json
import argparse
from TemplateTranslate import TemplateTranslate
from DBAnalyze import DBAnalyze

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='The database code to orther code.')
    parser.add_argument('-db', required=True, help='The database file path.')
    parser.add_argument('-intem',metavar='input_template', required=True, help='The translate code template.')
    parser.add_argument('-outem',metavar='output_template', required=True, help='The general code output path.')
    args = parser.parse_args()
    dbInfo = DBAnalyze(args.db)
    tt = TemplateTranslate(args.intem, args.outem, dbInfo)
    tt.translate()
    # dbInfo = DBAnalyze("C:\\Users\\Fantasy\\Desktop\\tutor.sql")
    # tt = TemplateTranslate("D:\\GitHub\\DBToCode\\template\\java\\SpringBoot", "C:\\Users\\Fantasy\\Desktop\\outputdir", dbInfo)
    # tt.translate()
    # print("Translate is success")