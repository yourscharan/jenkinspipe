package base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;

import utils.ConfigReader;
import utils.DriverFactory;
import utils.ExtentManager;
import utils.ExtentTestManager;
import utils.ScreenshotUtil;

public class BaseTest {

	protected WebDriver driver;
	protected ExtentTest test;

	@BeforeMethod
	public void setUp() {
		driver = DriverFactory.getDriver(ConfigReader.getProperty("browser"));
		driver.get(ConfigReader.getProperty("baseUrl"));

		test = ExtentManager.getInstance().createTest(this.getClass().getSimpleName());
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
			test.fail("Test Failed: " + result.getThrowable()).addScreenCaptureFromPath(screenshotPath); // 🔑 absolute
			
			//- A small thumbnail (100x150 px) will appear in the Extent Report.
			//Clicking the thumbnail opens the full-size screenshot in a new tab.

			test.info("<a href='" + screenshotPath + "' target='_blank'>" + "<img src='" + screenshotPath
					+ "' height='100' width='150'/></a>");
			
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.pass("Test Passed");
		} else {
			test.skip("Test Skipped");
		}

		if (driver != null) {
			driver.quit();
		}
	}

	@AfterSuite
	public void generateReport() {
		ExtentManager.getInstance().flush(); // 🔑 Flush once after all tests
	}

	@BeforeMethod(enabled = false) // this method is for parallel testing using testngxml file
	@Parameters("browser")
	public void crossBrowser(String browser) {
		driver = DriverFactory.getDriver(browser);
		driver.get(ConfigReader.getProperty("baseUrl"));
	}

}
