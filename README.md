# Assessment-
Assumptions

We use selenium 4.13.0 dependency Thats why not used ChromeDriver exe file 
The login page URL is: https://app.germanyiscalling.com/common/login/.

Valid credentials used for the successful login test are:
	Username: nagawadepranav4@gmail.com
	Password: Pranjali@123

Invalid credentials used for the unsuccessful login test are:
	Username: pranjli@gmail.com
	Password: Pranjali@7723

The expected URL after a successful login is: https://app.germanyiscalling.com/cv/upload/.
The error message for an unsuccessful login is: "Please enter a correct username and password. 
------------------------------------------------------------------------------------------------------
TestLayer.java: Contains two test cases:
	testSuccessfulLogin: Validates successful login using correct credentials.
	testUnsuccessfulLogin: Validates error handling with incorrect credentials.

-------------------------------------------------------------------------------------
Dependencies
selenium-java
Selenium WebDriver
TestNG 
ExtentReports 
----------------------------------------------------------------------------------------
Excution Flow
BeforeSuite: Initializes the ExtentReports object once before all tests.
BeforeMethod: Initializes the WebDriver and opens the login page before each test.

Test Cases:
	testSuccessfulLogin: Performs login with valid credentials and checks the URL.
	testUnsuccessfulLogin: Attempts login with invalid credentials and verifies the error message.

AfterMethod: Closes the browser after each test.
AfterSuite: Flushes the ExtentReports after all tests have run.
