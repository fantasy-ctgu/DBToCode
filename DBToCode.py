# coding=UTF-8
'''
@Author: Fantasy
@Date: 2020-01-15 14:21:57
@LastEditors  : Fantasy
@LastEditTime : 2020-01-15 15:24:35
@Descripttion: 
@Email: 776474961@qq.com
'''
import json
from TemplateTranslate import TemplateTranslate
from DBAnalyze import DBAnalyze

if __name__ == "__main__":
    dbInfo = DBAnalyze('testdb.sql')
    tt = TemplateTranslate("./template/java/springboot", "./out", dbInfo)
    tt.translate()
    # fp = open("./template/java/springboot/conf.json", "r+", encoding="utf-8")
    # conf = json.load(fp)
    # print(conf['typeTranslate.typeMap'])