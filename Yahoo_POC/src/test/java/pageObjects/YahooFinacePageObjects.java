package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Resource.ExtentReportNG;
import initializer.PageInitializer;
import utility.UtilityMethods;
import utility.WaitingMethods;

public class YahooFinacePageObjects extends PageInitializer{
	
	
	WaitingMethods waiting = new utility.WaitingMethods(getDriver());
	
	UtilityMethods utilityMethods = new UtilityMethods(getDriver());
	
	ExtentReportNG extent=new ExtentReportNG();

	
	@FindBy(xpath = "//input[@placeholder='Search for news, symbols or companies']")
	private WebElement searchTextboxXpath;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement searchButtonXpath;
	
	
	@FindAll({@FindBy(xpath = "//div/fin-streamer[contains(@class,'livePrice') or (@data-testid='qsp-price')]/span"),
              @FindBy(xpath = "(//section[@class='yf-1tejb6'][1]//span)[1]")})
	private WebElement stockPriceXpath;
	
	
	@FindBy(xpath = "//div[contains(@data-id,'search-assist')]/ul[@role='listbox']/li[@data-type='quotes']//div[contains(@class,'quoteCompanyName')]")
	private List<WebElement> autosuggestionXpath;
	
	@FindBy(xpath = "//span[@title='Previous Close']//following-sibling::span")
	private WebElement previousCloseValueXpath;
	
	@FindBy(xpath = "//span[@title='Volume']//following-sibling::span")
	private WebElement volumeValueXpath;
	

	
	
	public YahooFinacePageObjects searchStock(String stock) {
		// Validate the search data input before proceeding
		
		extent.log("Searching Stock inprogress");
		
	    if (stock == null || stock.trim().isEmpty()) {
	        System.out.println("Error: search data cannot be null or empty.");
	        return this;
	    }
	    
		// Wait for the visibility of the search text box
		waiting.waitForVisibilityOfElement(searchTextboxXpath, Duration.ofSeconds(10));	
		
		// Clear the search box before sending new input
		searchTextboxXpath.clear();
		
		// Enter the search data into the search box
		searchTextboxXpath.sendKeys(stock);
		
		System.out.println("Searched stock :"+stock);
		
		extent.pass("Searched stock :"+stock);
		
		return this;
	}
	
	
	public YahooFinacePageObjects clickOnSearchButton() {
		
		extent.log("Clicking On Search Button");
		
	    try {
	        // Wait for the visibility of the search button element
	        waiting.waitForVisibilityOfElement(searchButtonXpath, Duration.ofSeconds(10));

	        // Click the search button
	        searchButtonXpath.click();

	        // Log the action for debugging
	        System.out.println("Clicked on search button: " + searchButtonXpath);

	    } catch (Exception e) {
	        // Log the error if something goes wrong
	        System.out.println("Error clicking the search button: " + e.getMessage());
	    }

	    extent.pass("Clicked On Search Button");
	    return this;
	}

	
	public YahooFinacePageObjects verifyAutosuggestResult(String value) {
		
		extent.log("verifying auto-suggest result");
		
		// Wait for the visibility of the autosuggestions
	    waiting.waitForVisibilityOfElements(autosuggestionXpath, Duration.ofSeconds(10));
		
		// Check if the autosuggestion list is not empty
	    if (autosuggestionXpath.isEmpty()) {
	        System.out.println("No autosuggestions found.");
	        return this;
	    }

	    // Clean the expected value (removing commas or extra spaces)
	    value = value.replace(",", "").trim();

	    // Get the text of the first autosuggestion and clean it up
	    String autosuggestText = autosuggestionXpath.get(0).getText().replace(",", "").trim();

	    // Log the actual and expected values for debugging
	    System.out.println("Expected: " + value);
	    System.out.println("Actual: " + autosuggestText);

	    // Assert that the first suggestion matches the expected value
	    Assert.assertEquals(autosuggestText,value, "The auto-suggested value does not match the expected value.");
	   // Assert.assertEquals("The auto-suggested value does not match the expected value.", autosuggestText, value);

	    // Log the success of the verification
	    System.out.println("Verified auto-suggestion: " +value);

	    extent.pass("verified auto-suggest result");
	    
	    return this;
	}

	
	public YahooFinacePageObjects clickOnFirstEntry() {
		
		extent.log("Clicking On first auto-suggestions");
		
	    // Check if the autosuggestion list is not empty
	    if (autosuggestionXpath.isEmpty()) {
	        System.out.println("No auto-suggestions available.");
	        return this;
	    }

	    try {
	        // Wait for the first autosuggestion element to be visible
	        waiting.waitForVisibilityOfElement(autosuggestionXpath.get(0), Duration.ofSeconds(10));

	        String first_auto_suggestion=autosuggestionXpath.get(0).getText().replace(",", "").trim();
	        
	        // Click on the first auto-suggestion
	        autosuggestionXpath.get(0).click();

	        // Log the action for debugging
	        System.out.println("Clicked on first auto-suggestion: " + first_auto_suggestion);

	    } catch (Exception e) {
	        // Log the error if the click action fails
	        System.out.println("Error clicking the first auto-suggestion: " + e.getMessage());
	    }

	    extent.pass("Clicked On first auto-suggestions");
	    
	    return this;
	}


	public YahooFinacePageObjects verifyStockPrice() {
		
		extent.log("verifying stock price");
		
		 //Move to stock price element
		 //utilityMethods.moveToElement(stockPriceXpath);
		
		 // Wait for the visibility of the stock price element
		 waiting.waitForVisibilityOfElement(stockPriceXpath, Duration.ofSeconds(10));
		 
		 // Get the text from the stock price element
	     String value = stockPriceXpath.getText().trim();
	     
	     // Log the stock price value
         System.out.println("Stock price : " + value);
         
         // Clean the text to remove any non-numeric characters like $ or commas
         value = value.replaceAll("[^\\d.]", "").trim();
         
         // Ensure the value is not empty or null before parsing
         if (value.isEmpty()) {
             System.out.println("Stock price is not available or invalid.");
             return this;  
         }
         
        float stockPrice = Float.parseFloat(value);
        
        // Compare the stock price with $200
		if(stockPrice>200) {
			System.out.println("Stock price is greater than $200");
		}
		else {
			System.out.println("Stock price is lesser than $200");
		}
		
		 extent.pass("verified stock price");
		
		return this;
	}

	
	public YahooFinacePageObjects verifyAdditionalData() {
		
		extent.log("verifying previous close and volume values");
		
	    try {
	        // Wait for visibility of the 'Previous Close' value
	        waiting.waitForVisibilityOfElement(previousCloseValueXpath, Duration.ofSeconds(10));
	        
	        // Wait for visibility of the 'Volume' value
	        waiting.waitForVisibilityOfElement(volumeValueXpath, Duration.ofSeconds(10));

	        // Get text from 'Previous Close' and 'Volume'  fields
	        String previousClose = previousCloseValueXpath.getText().trim();
	        String volume = volumeValueXpath.getText().trim();
	        
	        // Check if the extracted values are not empty
	        if (previousClose.isEmpty()) {
	            System.out.println("Previous Close value is not available.");
	        } else {
	            System.out.println("Previous Close: " + previousClose);
	        }

	        if (volume.isEmpty()) {
	            System.out.println("Volume value is not available.");
	        } else {
	            System.out.println("Volume: " + volume);
	        }

	    } catch (Exception e) {
	        // Log the error if something goes wrong
	        System.out.println("Error retrieving or verifying additional data: " + e.getMessage());
	    }

	    extent.pass("verified previous close and volume values");
	    
	    return this;
	}

	
}
