# nts_spring_boot
This is a Spring boot application, created to develop the secured API's for NTS Application.   

# Here is software requirements:

1. Java: 17
2. Maven: 3.8.6
3. STS: 4(Recommend code editor, but mandatory)
4. Git: latest version

# Process to check in:

1. Create your own branch eg: develop/navaneeth.
2. Clone from it.
3. Run the db_setup.sql script in your local MySql instance. 
4. Update your local user name, password and spring.datasource.url=jdbc:mysql://localhost:3306/nts_db in application.properties file (Don't push this changes to remote github)
5. Do maven clean install (Use maven build, goal shoud be "clean install"). 
6. Up the server.
7. Hit localhost:8080/nts/demo (To test the installation)

# Code Integrity

1. Don't copy any code from online, you can refer the online code.
2. Don't merge your local branch to master branch without getting the peer review. 
3. Make sure you cover the necessary test cases before you push (code coverage expected is 80%).
4. Have proper variable and function naming conventions.
5. Have proper commit names to your code checkin.

Thanks for being part of Hugs for Bugs team. We will collaborate and learn together. 
Happy Coding!
