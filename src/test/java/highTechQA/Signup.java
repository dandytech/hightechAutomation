package highTechQA;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Signup extends reuse {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	// launch the website
	@BeforeTest
	public void lauchPage() {
		init();
		launchApp();
		confirmPage();

	}

	@Test
	public void TC_1_signup() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Sign Up']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter Username']")).sendKeys("Test8");
		driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("Password8");
		driver.findElement(By.xpath("//button[text()='Submit']")).click();

		WebElement validationElement = driver.findElement(By.xpath("//form[@class='signup-form']"));

		String formText = validationElement.getText();

		// Assert that the form does not contain the text 'Test failed'
		Assert.assertFalse(formText.contains("Test failed"), "Test failed: Form contains the text 'Test failed'.");

		
		tearDown();
	}

	// Take screenshot on test failure
	@AfterMethod
	public void Aftermethod(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			TakeScreenshot(result.getName());
		}
	}
}