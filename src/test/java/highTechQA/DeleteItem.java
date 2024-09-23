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
		// driver.findElement(By.xpath("//a[text()='Sign Up']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter Username']")).sendKeys("Test8");
		driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("Password8");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		
		WebElement validationElement = driver.findElement(By.xpath("//form[@class='signup-form']"));
		String formText = validationElement.getText();
		// Assert that the form does not contain the text 'Test failed'
		Assert.assertFalse(formText.contains("LIST OF ITEMS"), "Test failed: Form contains the text 'LIST OF ITEMS");
		Thread.sleep(3000);
		
		String itemToDelete = "Shoe";
		String newName = "Jeans";
		String newDescription = "Blue Men Jeans with crossing Belt";
		
		
		
		List<WebElement> items = driver.findElements(By.xpath("//p[@class='container2']"));

		// Get the count of characters that are listed (snapshot of the list size)
		int containerCount = items.size();
		System.out.println("Total characters found: " + containerCount);

		// Iterate over the list
		for (int i = 0; i < containerCount; i++) {
			String containerText = items.get(i).getText();
			//System.out.println("Character texts: " + containerText);

			// Check if the character is "alive"
			if (containerText.contains(itemToDelete)) {
				WebElement characterToClick = driver.findElements(By.xpath("//button[text()='Delete']")).get(i);

				// Wait until the element is clickable
				wait.until(ExpectedConditions.elementToBeClickable(characterToClick));

				// Scroll into view if necessary (optional)
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", characterToClick);

				// Click the element
				characterToClick.click();

				System.out.println("About to Delete: " + containerText);
				
				driver.findElement(By.xpath("//input[@placeholder='Item Name']")).sendKeys(newName);
				driver.findElement(By.xpath("//input[@placeholder='Description']")).sendKeys(newDescription);
				driver.findElement(By.xpath("//button[text()='Delete Item']")).click();
				
				
				Assert.assertTrue(validationElement.contains("Failed to delete item"));
				Thread.sleep(3000);
				tearDown();
				
			}
		}

}
