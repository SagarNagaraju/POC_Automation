package utility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import mainClass.MainWebDriverClass;

public class UtilityMethods extends MainWebDriverClass {

	WebDriver driver;

	WaitingMethods waitingMethods;

	public UtilityMethods(WebDriver driver) {
		this.driver = driver;
	}

	// To accept alert
	public void alertOK() {
		waitingMethods = new WaitingMethods(driver);
		waitingMethods.waitForAlertToBePresent(Duration.ofSeconds(10));
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	// To dismiss alert
	public void alertCancel() {
		waitingMethods = new WaitingMethods(driver);
		waitingMethods.waitForAlertToBePresent(Duration.ofSeconds(10));
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	// To fetch alert text
	public String getAlertText() {
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText().trim();
		return alertText;
	}

	// Verify alert text
	public boolean verifyAlertText(String expectedAlertText) throws Exception {
		boolean result = getAlertText().replace("\n", "").trim().equalsIgnoreCase(expectedAlertText);
		alertOK();
		return result;
	}
	
	// Move to a element
	public void moveToElement(WebElement element) {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(element).build().perform();
	}

	// Move to a element and click
	public void moveToElementAndClick(WebElement element) {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(element).click().build().perform();
	}

	public void scrollThePageForListOfElements(List<WebElement> locatorValue, int indexValue) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();",
				locatorValue.get(indexValue));

	}

	// Move to the bottom of the page
	public void scrollThePageToBottom() {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	// Move to the top of the page
	public void scrollThePageToTop() {
		((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}

	// scroll till element
	public void scrollTillWebElement(WebElement webElement) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", webElement);
	}

	// Drag and drop
	public void dragAndDropElement(WebElement source, WebElement target) {
		Actions actions = new Actions(getDriver());
		actions.dragAndDrop(source, target).build().perform();
	}

	// Drag and drop
	public void dragAndDropElement(WebElement source, int xOrdinate, int yOrdinate) {
		Actions actions = new Actions(getDriver());
		actions.dragAndDropBy(source, xOrdinate, yOrdinate).build().perform();
	}

}
