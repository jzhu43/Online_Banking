# Online-Banking

Online-Banking is a Spring Boot Java application that allows users to create a bank account with ICIN Bank.
There are two sides of the application, Users and Admins. Users are able to deposit, withdraw, and transfer money. Users may also request checkbooks and edit their user account information. Admins are able to confirm checkbook requests and enable/disable users. Disabling a user prevents
the user from making a deposit, withdrawal, or transfer.

## Run this program using Eclipse
1. Clone the master branch or download the zip file and import it into Eclipse IDE.
2. Open MySql Workbench and log into your local instance of MYSQL80 with the connection to localhost:3306.
3. Create a schema/database called "banking" in your MySql database instance.
4. Go back to Eclipse and open the project file application.properties located at src/main/resources.
5. In the applications.properties file, enter your MySql instance credentials on line 2 and 3 and save.
6. Right click the project in your Eclipse project explorer and click **Maven > Update Project**.
7. Right click the project again and click on **Run As > Maven Build** with the configuration of **clean verify**.
8. Right click the project again and click on **Run As > Java Application**.
9. Open a browser of your choice and navigate to localhost:8080 to be redirected to the sign in page for the user portal.
10. To access the admin login page, navigate to localhost:8080/admin/login

## Technologies Used
**Backend:**\
Java\
Spring Boot\
MySql

**Frontend:**\
HTML5\
CSS3
