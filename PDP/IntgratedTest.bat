@echo off
start "New Window" cmd /c startMvnTest.bat
cd "ot_pdp_crs_codesys\preclude\Test Automation\Execute"
start "New Window" cmd /c Powershell.exe -executionpolicy remotesigned -File .\Execute-Tests.ps1