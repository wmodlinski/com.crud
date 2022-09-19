call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openbrowser
echo.
echo There were errors
goto fail

:openbrowser
start "" http://localhost:8080/crud/v1/tasks
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Show tasks script finished