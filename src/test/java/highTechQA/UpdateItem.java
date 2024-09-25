package highTechQA;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UpdateItem extends reuse {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	// launch the website
	@BeforeTest
	public void lauchPage() {
		init();
		launchApp();
		confirmPage();

	}

	@Test
	public void TC_4_updateItem() throws InterruptedException {
		// driver.findElement(By.xpath("//a[text()='Sign Up']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter Username']")).sendKeys("Mary");
		driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("Password");
		driver.findElement(By.xpath("//button[text()='Login']")).click();

		WebElement validationElement = driver.findElement(By.xpath("//p[text()='LIST OF ITEMS']"));
		String formText = validationElement.getText();
		// Assert that the form does not contain the text 'Test failed'
		Assert.assertEquals(formText, "LIST OF ITEMS");
		Thread.sleep(3000);

		String itemToUpdate = "Pen";
		String newItemChanges = "Pencile";

		List<WebElement> items = driver.findElements(By.xpath("//p[@class='container2']"));

		// Get the count of characters that are listed (snapshot of the list size)
		int containerCount = items.size();
		System.out.println("Total characters found: " + containerCount);

		// Iterate over the list
		for (int i = 0; i < containerCount; i++) {
			String containerText = items.get(i).getText();
			// System.out.println("Character texts: " + containerText);

			// Check if the character is "alive"
			if (containerText.contains(itemToUpdate)) {
				WebElement characterToClick = driver.findElements(By.xpath("//button[text()='Edit']")).get(i);

				// Wait until the element is clickable
				wait.until(ExpectedConditions.elementToBeClickable(characterToClick));

				// Scroll into view if necessary (optional)
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", characterToClick);

				// Click the element
				driver.findElement(By.xpath("//button[text()='Edit']")).click();

				System.out.println("Updated: " + itemToUpdate);

				driver.findElement(By.xpath("//input[@placeholder='Updated Name']")).clear();
				driver.findElement(By.xpath("//input[@placeholder='Updated Name']")).sendKeys(newItemChanges);
				driver.findElement(By.xpath("//textarea[@placeholder='Updated Description']")).clear();
				driver.findElement(By.xpath("//textarea[@placeholder='Updated Description']"))
						.sendKeys("For Web App Automation");

				driver.findElement(By.xpath("//button[text()='Update Item']")).click();

			}
		}

		Thread.sleep(3000); //wait 3sec
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
