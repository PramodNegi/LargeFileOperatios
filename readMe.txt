
Run the Following Commands, in order to build and execute the Program from Command Prompt

command 1:  gradle build
    Once the build is completed(i.e. jar is created) trigger the following command

command 2: java -jar "<project_home>/build/libs/largeFileOperations-0.0.1-SNAPSHOT.jar "<JSON_FILE_PATH>"

In console it shows Following:
   All Alerted Events i.e. Event have duration more than 4ms
   All Alerted Events whose Duration is maximum.

Process to run the program from Eclipse IDE
   Select 'Run Configurations' from 'Run' menu 
   Search / Specify the Main Class com.csg.dts.processor.app.LargeFileProcessorApp
   Specify the File Path in Program Arguments under Arguments tab
   Hit Run button.


Asuumption Made:
JSON file will not have any Entry Twice with State "STARTED" with Same ID.
JSON file will not have any Entry Twice with State "FINISHED" with Same ID.
