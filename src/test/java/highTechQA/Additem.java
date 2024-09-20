package highTechQA;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Additem extends reuse {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	// launch the website
	@BeforeTest
	public void lauchPage() {
		init();
		launchApp();
		confirmPage();

	}

	@Test
	public void TC_3_addItem() throws InterruptedException {
		// driver.findElement(By.xpath("//a[text()='Sign Up']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter Username']")).sendKeys("Test8");
		driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("Password8");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		
		WebElement validationElement = driver.findElement(By.xpath("//form[@class='signup-form']"));
		String formText = validationElement.getText();
		// Assert that the form does not contain the text 'Test failed'
		Assert.assertFalse(formText.contains("Login failed: check your credentials and try again"), "Test failed: Form contains the text 'Login failed: check your credentials and try again'.");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='additembutton  ']")));
		driver.findElement(By.xpath("//button[@class='additembutton  ']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Item Name']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//input[@placeholder='Description']")).sendKeys("For Web App Automation");
		driver.findElement(By.xpath("//button[text()='Add Item']")).click();
		Thread.sleep(5000);
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



