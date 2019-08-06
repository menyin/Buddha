@echo off
rem /**
rem  * Copyright &copy; 2018-2019 <a href="https://cny.com/buddha">Buddha</a> All rights reserved.
rem  *
rem  * Author: 845257580@qq.com
rem  */
echo.
echo [��Ϣ] ��������·����
echo.
pause
echo.

cd %~dp0
cd..

call mvn clean

cd bin
pause