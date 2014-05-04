@echo off
if "%AKKO_ADDRESS%"=="" goto HELP

echo Type command and press enter
nc -u %AKKO_ADDRESS% 1985
GOTO END

:HELP
echo Address not set.  Try:
echo.
echo set AKKO_ADDRESS=(ip address)
echo.

:END