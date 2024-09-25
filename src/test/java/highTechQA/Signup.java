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
		
		//provide login details
		String username = "DanielA";
		String password = "Password";
		
		//sign up details
		driver.findElement(By.xpath("//a[text()='Sign Up']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter Username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Submit']")).click();

		//verification
		WebElement validationElement = driver.findElement(By.xpath("//p[text()='LOGIN']"));
		String formText = validationElement.getText();
		// Assert that the form contain the text 'LOGIN'
		Assert.assertEquals(formText, "LOGIN");
		
		Thread.sleep(3000);
		tearDown(); //close the browser
	}

	// Take screenshot on test failure
	@AfterMethod
	public void Aftermethod(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			TakeScreenshot(result.getName());
		}
	}
}