import os

def scanDir(filePath):
    files = os.listdir(filePath)
    for file in files:
        file_d = os.path.join(filePath,file)
        if os.path.isdir(file_d):
            scanDir(file_d)
        else:
            print("scan file:"+file_d)
            codeTranslate(file_d)

def codeTranslate(file):
    if not file.endswith('.java'):
        return
    with open(file,'rb+') as f:
        content = f.read()
        try:
            content.decode('utf8')
        except:
            print("start translate file:"+file)
            content = content.decode('gbk',errors="ignore").encode('utf8')
            f.seek(0)
            f.write(content)
if __name__ == '__main__':
    scanDir(".")