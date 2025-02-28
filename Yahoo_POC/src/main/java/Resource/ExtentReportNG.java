package Resource;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import mainClass.MainWebDriverClass;

public class ExtentReportNG extends MainWebDriverClass {
	
	public static ExtentReports extentReport;
	
	public static String extentReportPrefix;
	public static ThreadLocal<ExtentTest> extenttest=new ThreadLocal<>();
	
	public static ExtentReports getReports() {
		if(extentReport==null) {
			setUpExtentReport("POC_Yhoo");
		}
		
		return extentReport;
	}
	
	public static ExtentReports setUpExtentReport(String testName) {
		
		extentReport=new ExtentReports();
	    ExtentSparkReporter spark=new ExtentSparkReporter(System.getProperty("user.dir") + "/report/" +"POC_Yahoo_"+extentReportPrefix_Name(testName) +".html");
		extentReport.attachReporter(spark);
		extentReport.setSystemInfo("Tester", "Sagar N");
		extentReport.setSystemInfo("URL", "https://finance.yahoo.com/");
		extentReport.setSystemInfo("Browser", "Chrome");
		spark.config().setReportName("POC_Yahoo");
		spark.config().setDocumentTitle("Test Results");
		spark.config().setTheme(Theme.DARK);
		
		return extentReport;
		
	}
	
	public static String extentReportPrefix_Name(String testName) {
		
		String date=new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		extentReportPrefix=testName+"_"+date;
		return extentReportPrefix;
	}
	
	public static void flushReport() {
		extentReport.flush();
	}
	
	public synchronized static ExtentTest getTest() {
		return extenttest.get();
	}

	public synchronized static ExtentTest createTest(String name,String description) {
		ExtentTest test=extentReport.createTest(name, description);
		extenttest.set(test);
		return getTest();
	}
	
	public synchronized static void log(String message) {
		getTest().info(message);
	}
	
	public synchronized static void pass(String message) {
		getTest().pass(message);
	}
	
	public synchronized static void fail(String message) {
		getTest().fail(message);
	}
	
	
	public synchronized static void attachImg(String path) {
		getTest().addScreenCaptureFromPath(path);
	}
}
