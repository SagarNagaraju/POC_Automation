package initializer;

import org.openqa.selenium.support.PageFactory;

import mainClass.MainWebDriverClass;
import pageObjects.YahooFinacePageObjects;


public class PageInitializer extends MainWebDriverClass {

    // Initialize the YahooFinancePageObjects page object
    public YahooFinacePageObjects yahooFinancePage() {
        // Initialize the elements in the page object using PageFactory
        return PageFactory.initElements(getDriver(), YahooFinacePageObjects.class);
    }
    

}
