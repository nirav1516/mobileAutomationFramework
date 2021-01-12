# mobileAutomationFramework

# maven prject has to be told to use specific version of java using  
-properties  
----project.build.sourceEncoding>UTF-8</project.build.sourceEncoding  
----maven.compiler.source>1.8</maven.compiler.source  
----maven.compiler.target>1.8</maven.compiler.target  
-properties  
 
 # Listeners has to be declared both in testNG file as well as pom.xml under resources
 
 # ExtentReports are being used for reporting, that too has to be run as listener. //TO DO: try to integrate it as part of itestListener methods to get correct time stamp in the report.
 
 # Data and profile can be passed to mvn run like this:  
---mvn test -P regression -D deviceName=EMULATOR_PIXEL_3A_8