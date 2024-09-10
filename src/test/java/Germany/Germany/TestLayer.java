package Germany.Germany;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestLayer {
	WebDriver driver;
	static ExtentReports extent;
	ExtentTest test;

	@BeforeSuite
	public void setupExtentReport() {
		// Initialize ExtentReports once for the entire suite
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
	}

	@BeforeMethod
	public void setup() {
		// Initialize WebDriver before each test
		driver = new ChromeDriver();
		// open the URL
		driver.get("https://app.germanyiscalling.com/common/login/");
	}

	// Login Successful TestCase
	@Test(priority = 1)
	public void testSuccessfulLogin() {
		// Add a new test to ExtentReports
		test = extent.createTest("Test Successful Login");

		try {
			System.out.println("Executing testSuccessfulLogin");

			// find the username Textbox with help of xpath and findelement mathod
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("nagawadepranav4@gmail.com");

			// find the user password with help of xpath and findelement mathod
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Pranjali@123");

			// find the login button with help of xpath and findelement mathod
			driver.findElement(By.xpath("//button[text()='Log In']")).click();

			String expectedUrl = "https://app.germanyiscalling.com/cv/upload/";
			// Get the Currnet page URL
			Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
			test.pass("Login was successful.");
		} catch (AssertionError e) {
			test.fail("Login failed with unexpected URL: " + e.getMessage());
		} catch (Exception e) {
			test.fail("Login test failed with exception: " + e.getMessage());
		}
	}

	// Login invalid TestCase
	@Test(priority = 2)
	public void testUnsuccessfulLogin() {
		// Add a new test to ExtentReports
		test = extent.createTest("Test Unsuccessful Login");

		try {
			System.out.println("Executing testUnsuccessfulLogin");
			// find the username Textbox with help of xpath and findelement mathod
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("pranjli@gmail.com");

			// find the user password with help of xpath and findelement mathod
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Pranjali@7723");

			// find the login button with help of xpath and findelement mathod
			driver.findElement(By.xpath("//button[text()='Log In']")).click();

			// Use WebDriverWait to wait for the alert to be visible
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement text = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-danger']")));

			// getText is used to find the physical text

			String actualMessage = text.getText();
			String expectedMessage = "Please enter a correct username and password. Note that both fields may be case-sensitive.";
			// hard Asssertion
			Assert.assertEquals(actualMessage, expectedMessage);
			test.fail("Login failed with expected message: " + actualMessage);
		} catch (AssertionError e) {
			test.fail("Login failed but with unexpected message: " + e.getMessage());
		} catch (Exception e) {
			test.fail("Login test failed with exception: " + e.getMessage());
		}
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@AfterSuite
	public void tearDownExtentReport() {
		if (extent != null) {
			extent.flush();
		}
	}
}
