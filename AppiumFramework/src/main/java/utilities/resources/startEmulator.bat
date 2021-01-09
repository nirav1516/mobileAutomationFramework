echo %ANDROID_HOME%
set EMULATOR_NAME=%1
C:
cd %ANDROID_HOME%/emulator
emulator -avd %EMULATOR_NAME%