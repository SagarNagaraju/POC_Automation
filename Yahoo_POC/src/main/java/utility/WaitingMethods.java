package utility;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import mainClass.MainWebDriverClass;

public class WaitingMethods extends MainWebDriverClass{

	WebDriver driver;

	public WaitingMethods(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForVisibilityOfElement(WebElement element, Duration time) {
		new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOf(element));

	}
	
	public void waitForVisibilityOfElements(List<WebElement> element, Duration time) {
		new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfAllElements(element));
	}


	public void waitForAlertToBePresent(Duration time) {
		new WebDriverWait(driver, time).until(ExpectedConditions.alertIsPresent());
	}

	public void implicitWaitForSeconds(long seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
	
}
