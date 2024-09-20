package highTechQA;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class reuse {
	public WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	// Initialize Chrome driver
	public WebDriver init() {

		// Clear driver cache and setup ChromeDriver
		WebDriverManager.chromedriver().clearDriverCache().setup();

		// Create ChromeOptions instance
		ChromeOptions options = new ChromeOptions();

		// Add arguments to disable notifications and allow origin access
		options.addArguments("--disable-notifications");
		options.addArguments("--remote-allow-origins=*");

		// Initialize ChromeDriver with options
		driver = new ChromeDriver(options);

		return driver;

	}

	// Method to capture screenshot on test failure
	public void TakeScreenshot(String screenshotname) throws IOException {

		File screenshotfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotfile, new File("./screenshots/" + screenshotname + ".png"));
		System.out.println("Screenshot captured: " + screenshotname);

	}

	// Lauch App
	public void launchApp() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://qadaniel.netlify.app/");

	}

	//confirm the page
	public void confirmPage() {
		try {
			String pageTitle = driver.getTitle();
			String expectedTitle = "React App";
			Assert.assertEquals(expectedTitle, pageTitle, "Page title mismatch");
			System.out.println("Page Title: " + pageTitle);
		} catch (Exception e) {
			System.err.println("Error getting page title: " + e.getMessage());
		}

	}

	public void tearDown() {
		driver.quit();
		
	}

}
