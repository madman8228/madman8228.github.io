#### Android系统中实现自动点击屏幕的方法
android提供的 input tap命令可以模拟点击屏幕上具体某一个位置，
例如input tap 50 100 点击屏幕上 x:50 y:100位置
所以可以在Android 系统中写一个shell脚本，实现一系列的点击事件。
也可以实现一个windows批处理脚本通过adb连接到设备上,实现点击事件。
批处理文件sendCmd.bat: 
 ```
@echo off
set count=1
adb shell input tap 5 5
ping -n 5 127.0.0.1>null
adb shell input tap 50 200
ping -n 5 127.0.0.1>null
:start
echo %count%
adb shell input tap 50 200
ping -n 6 127.0.0.1>null
adb shell input tap 700 200
ping -n 10 127.0.0.1>null
adb shell input tap 20 50
ping -n 6 127.0.0.1>null
set /a count=count+1
goto start
```
