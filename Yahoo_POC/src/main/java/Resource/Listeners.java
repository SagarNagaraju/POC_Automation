package Resource;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import mainClass.MainWebDriverClass;

public class Listeners extends MainWebDriverClass implements ITestListener {
	
	public WebDriver driver;
	
	ExtentTest test;
	
	String concatenate=".";
	
	ExtentReports extent = ExtentReportNG.setUpExtentReport("POC_Yahoo_Automation");
	
	public Listeners() throws IOException{
		super();
	}
	
	public void onTestStart(ITestResult result) {
		
		ExtentReportNG.getReports();
		test=ExtentReportNG.createTest(result.getMethod().getMethodName(),result.getName());
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		}
		
		catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void onTestSuccess(ITestResult result) {
		
		ITestListener.super.onTestSuccess(result);
        test.log(Status.PASS, "Test Passed");		
        String filePath= concatenate+getScreenshot(result.getMethod().getMethodName(), driver);
        ExtentReportNG.attachImg(filePath);
	}
	
    public void onTestFailure(ITestResult result) {
		
		ITestListener.super.onTestFailure(result);
        test.log(Status.FAIL, "Test Failed:"+result.getMethod().getMethodName());
        test.fail(result.getThrowable());
        String filePath= concatenate+getScreenshot(result.getMethod().getMethodName(), driver);
        ExtentReportNG.attachImg(filePath);
	}
    
    public void onTestSkipped(ITestResult result) {
		
		ITestListener.super.onTestSkipped(result);
        
	}
    
 public void onTestFailedButWithSuccessPercentage(ITestResult result) {
		
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
        
	}
 
 public void onTestFailedWithTimeout(ITestResult result) {
		
		ITestListener.super.onTestFailedWithTimeout(result);
     
	}
 
 public void onStart(ITestContext context) {
		
		ITestListener.super.onStart(context);
  
	}
 
 public void onFinish(ITestContext context) {
		
		//ITestListener.super.onFinish(result);
	 ExtentReportNG.extentReport.flush();
  
	}


}
