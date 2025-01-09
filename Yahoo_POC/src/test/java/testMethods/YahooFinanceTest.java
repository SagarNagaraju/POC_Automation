package testMethods;

import org.testng.annotations.Test;

import Resource.ExtentReportNG;

import org.testng.annotations.Test;

import initializer.PageInitializer;
import utility.TestDataPropertyFile;

public class YahooFinanceTest extends PageInitializer {
	
	TestDataPropertyFile data = new TestDataPropertyFile();
	
	ExtentReportNG extent=new ExtentReportNG();

	// Search for the stock and verify stock Details
    @Test
    public void verifyStockDetailsFunctionality() {
    	extent.log("verification of Stock Details Functionality Started...");
        yahooFinancePage()
            .searchStock(data.getSearchStock())                              // Searches for TSLA
            .verifyAutosuggestResult(data.getStockName())                    // Verifies the autosuggest result
            .clickOnFirstEntry()                                             // Clicks on the first autosuggestion
            .verifyStockPrice()                                              // Verifies the stock price
            .verifyAdditionalData();                                         // Verifies the additional data like "Previous Close" and "Volume"
        extent.log("verification of Stock Details Functionality Completed...");
    }

}
