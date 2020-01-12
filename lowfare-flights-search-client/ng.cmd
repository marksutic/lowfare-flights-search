:: Created by npm, please don't edit manually.
@ECHO OFF

SETLOCAL

SET "NODE_EXE=%~dp0\node\node.exe"

SET "NG_CLI=%~dp0\node_modules\@angular\cli\bin\ng"

"%NODE_EXE%" "%NG_CLI%" %*
