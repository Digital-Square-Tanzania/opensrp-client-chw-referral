@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  Gradle startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

@rem Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS="-Xmx64m" "-Xms64m"

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
set JAVA_VERSION=
for /f "tokens=3 delims= " %%v in ('"%JAVA_EXE%" -version 2^>^&1 ^| findstr /i "version"') do set JAVA_VERSION=%%~v
set JAVA_VERSION=%JAVA_VERSION:"=%

for /f "tokens=1,2 delims=.-" %%a in ("%JAVA_VERSION%") do (
    set JAVA_MAJOR=%%a
    set JAVA_MINOR=%%b
)

if "%JAVA_MAJOR%"=="1" set JAVA_MAJOR=%JAVA_MINOR%
if "%JAVA_MAJOR%"=="11" goto readyToExecute

if defined JAVA11_HOME goto useJava11Home
if defined JDK11_HOME goto useJdk11Home

echo.
echo ERROR: This project requires JDK 11 for the Gradle build.
echo Current Java version: %JAVA_MAJOR%
echo.
echo Install JDK 11 and set JAVA_HOME or JAVA11_HOME before running Gradle.

goto fail

:useJava11Home
set JAVA_HOME=%JAVA11_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe
if exist "%JAVA_EXE%" (
    echo Using Java 11 from JAVA11_HOME because this build is configured to run with JDK 11.
    goto readyToExecute
)

:useJdk11Home
set JAVA_HOME=%JDK11_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe
if exist "%JAVA_EXE%" (
    echo Using Java 11 from JDK11_HOME because this build is configured to run with JDK 11.
    goto readyToExecute
)

echo.
echo ERROR: This project requires JDK 11 for the Gradle build.
echo Current Java version: %JAVA_MAJOR%
echo.
echo Install JDK 11 and set JAVA_HOME or JAVA11_HOME before running Gradle.

goto fail

:readyToExecute
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar

@rem Execute Gradle
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% "-Dorg.gradle.appname=%APP_BASE_NAME%" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable GRADLE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%GRADLE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
