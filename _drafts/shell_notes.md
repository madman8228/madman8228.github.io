---
layout: post
title:  "shell常用命令和方法"
categories: linux
tags:  shell
---

#### shell的常用命令和方法速查 
**find ./ -type f | wc -l**  
统计当前目录和子目录下所有文件  
**ls -l | grep "^-" | wc -l**  
统计当前目录下文件个数  
**ls -l | grep "^d" | wc -l**  
统计当前文件夹下目录的个数  
**cat /dev/null > test.md5**  
清除test.md5文件中的内容  
**set -u**  
执行时使用到未定义过的变量，则显示错误信息，防止脚本中有未定义的变量导致发生错误  
**set -x**  
用于脚本调试，把它下面的命令打印到屏幕 **set -x** 开启 **set +x** 关闭     

```   
#!/bin/sh
set -x
ehco "this is a shell script file"
...
```  

