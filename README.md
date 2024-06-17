# EVENT_MANAGEMENT_SYSTEM
### Compiling and Running the Application

To compile and run the Event Management System application, follow these steps:

#### Navigate to the Project Directory

Open PowerShell and navigate to the root directory of your project:

```powershell
cd \EVENT_MANAGMENT_SYSTEM
```
### Compile All Java Files
Use the following PowerShell commands to find all .java files and compile them:
```
powershell
$javaFiles = Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -cp ".;.\mysql-connector-j-8.3.0.jar" $javaFiles
```
Run the Application
After successfully compiling the application, you can run it with the following command:
```
powershell
java -cp ".;.\mysql-connector-j-8.3.0.jar" ems.main.Main
```
By following these steps, you should be able to compile and run the Event Management System application successfully.
