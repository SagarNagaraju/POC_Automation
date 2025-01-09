package mainClass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MainWebDriverClass {

	public static WebDriver driver;
	
	
	  public  WebDriver getDriver() {
		  return driver; 
		  }
	 
    @BeforeClass(alwaysRun = true)
    @Parameters({"URL","Browser","Runheadless"})
	public WebDriver initDriver(String url,String browser,String runheadless) {
		
    	if(browser.equals("chrome")) {
    		// Using WebDriverManager to manage ChromeDriver binary
            WebDriverManager.chromedriver().setup();
            
            // Configuring Chrome Options
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");                                 
            
            if(runheadless.equalsIgnoreCase("true")) {
            	options.addArguments("--headless"); 
            	System.out.println("Execution started in Headless mode");
            }
            else {
            	System.out.println("Execution started in Window mode");
            }
            // Create a WebDriver instance with ChromeOptions
             driver = new ChromeDriver(options);
             
             System.out.println("Launching " + browser + " browser");
             
            // Navigate to the desired URL
             driver.get(url);
    	}
    	if(browser.equals("firefox")) {
    		// Using WebDriverManager to manage FirefoxDriver binary
            WebDriverManager.firefoxdriver().setup();
            
            // Configuring Firefox Options
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--start-maximized");                                 
            
            if(runheadless.equalsIgnoreCase("true")) {
            	options.addArguments("--headless"); 
            	System.out.println("Execution started in Headless mode");
            }
            else {
            	System.out.println("Execution started in Window mode");
            }
            // Create a WebDriver instance with FirefoxOptions
             driver = new FirefoxDriver(options);
             
             System.out.println("Launching " + browser + " browser");
             
            // Navigate to the desired URL
             driver.get(url);
    	}
    	if(browser.equals("edge")) {
    		// Using WebDriverManager to manage EdgeDriver binary
            WebDriverManager.edgedriver().setup();
            
            // Configuring Edge Options
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--start-maximized");                                 
            
            if(runheadless.equalsIgnoreCase("true")) {
            	options.addArguments("--headless"); 
            	System.out.println("Execution started in Headless mode");
            }
            else {
            	System.out.println("Execution started in Window mode");
            }
            // Create a WebDriver instance with EdgeOptions
             driver = new EdgeDriver(options);
             
             System.out.println("Launching " + browser + " browser");
             
            // Navigate to the desired URL
             driver.get(url);
    	}

        
		
        return driver;                          
		
	}
    
    @AfterClass
    public void cleanup() {
    	 // Cleanup browser and session   	 
         // clear cookies
         clearCookies();

         // reset test data
         resetTestData();
         
         if (driver != null) {
             driver.quit();
         }
    }
    
    private void clearCookies() {
        driver.manage().deleteAllCookies();
        System.out.println("Cookies deleted successfully.");
    }

    private void resetTestData() {
        // Reset the application data here (API call, DB cleanup, etc.)
        System.out.println("Test data reset successfully.");
    }
    
    public String getScreenshot(String testCaseName,WebDriver driver) {
    	
    	TakesScreenshot ts = (TakesScreenshot) driver;
    	String imagePath = "./report/screenshots/"+ testCaseName + ".png";
    	File source =ts.getScreenshotAs(OutputType.FILE);
    	File file =new File(imagePath);
    	try {
    		FileUtils.copyFile(source, file);
    	}
    	catch (IOException e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
		return imagePath;
    }
 
    public static String timestamp() {
    	return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
    }

}
