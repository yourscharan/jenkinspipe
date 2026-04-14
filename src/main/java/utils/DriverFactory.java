package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	public static WebDriver getDriver(String browser) {
		WebDriver driver;
		switch (browser.toLowerCase()) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "E:\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "chrome":
		default:
			System.setProperty("webdriver.chrome.driver", "E:\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}
		driver.manage().window().maximize();
		return driver;
	}

}
