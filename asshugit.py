import os, glob, subprocess

dirlist = glob.glob("D:\myapplication\*")
repolist = [i.split('\\')[-1] for i in dirlist]

for (reponame,folder) in zip(repolist, dirlist):    
    os.chdir(folder)
    os.system('git init')
    os.system('git add .')
    os.system('git commit -m "first commit"')
    os.system("git remote add origin https://github.com/aashutosh-rana/{}.git".format(reponame))
    os.system("git remote set-url origin https://github.com/aashutosh-rana/{}.git".format(reponame))
    os.system('git push origin master --force')