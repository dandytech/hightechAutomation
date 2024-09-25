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

		// Login details
		String username = "Mary";
		String password = "Password";

		// Details of Item to Add
		String name = "Pen";
		String description = "For Writing";

		// Login
		driver.findElement(By.xpath("//input[@placeholder='Enter Username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		Thread.sleep(5000); // wait 3 sec

		// Verification
		WebElement validationElement = driver.findElement(By.xpath("//p[text()='LIST OF ITEMS']"));
		String formText = validationElement.getText();
		// Assert that the form contain the text 'LIST OF ITEMS'
		Assert.assertEquals(formText, "LIST OF ITEMS");
		System.out.print("About to add" + name);

		// add item form
		driver.findElement(By.xpath("//button[text()='   + Add Item']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Item Name']")).sendKeys(name);
		driver.findElement(By.xpath("//textarea[@placeholder='Description']")).sendKeys(description);
		driver.findElement(By.xpath("//button[text()='Add Item']")).click();
		Thread.sleep(3000); // wait 3 sec
		tearDown(); // close the bowser
	}

	// Take screenshot on test failure
	@AfterMethod
	public void Aftermethod(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			TakeScreenshot(result.getName());
		}
	}
}
