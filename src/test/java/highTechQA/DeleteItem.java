package highTechQA;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DeleteItem extends reuse {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	// launch the website
	@BeforeTest
	public void lauchPage() {
		init();
		launchApp();
		confirmPage();

	}

	@Test
	public void TC_5_DeleteItem() throws InterruptedException {
		// Login details
		String username = "Mary";
		String password = "Password";

		//Item name to delete
		String itemToDelete = "Pen";
		
		// driver.findElement(By.xpath("//a[text()='Sign Up']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter Username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Login']")).click();

		WebElement validationElement = driver.findElement(By.xpath("//p[text()='LIST OF ITEMS']"));
		String formText = validationElement.getText();
		// Assert that the form does not contain the text 'Test failed'
		Assert.assertEquals(formText, "LIST OF ITEMS");
		Thread.sleep(3000);

		

		List<WebElement> items = driver.findElements(By.xpath("//p[@class='container2']"));

		// Get the count of characters that are listed (snapshot of the list size)
		int containerCount = items.size();
		System.out.println("Total characters found: " + containerCount);

		// Iterate over the list
		for (int i = 0; i < containerCount; i++) {
			String containerText = items.get(i).getText();
			// System.out.println("Character texts: " + containerText);

			// Check if the character is "alive"
			if (containerText.contains(itemToDelete)) {
				WebElement characterToClick = driver.findElements(By.xpath("//button[text()='   Delete']")).get(i);

				// Wait until the element is clickable
				wait.until(ExpectedConditions.elementToBeClickable(characterToClick));

				// Scroll into view if necessary (optional)
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", characterToClick);

				// Click the element
				driver.findElement(By.xpath("//button[text()='   Delete']")).click();

				System.out.println("Deleted: " + itemToDelete);

				driver.findElement(By.xpath("//button[text()='Delete Item']")).click();

			}
		}

		Thread.sleep(5000);

		tearDown();

	}
}
