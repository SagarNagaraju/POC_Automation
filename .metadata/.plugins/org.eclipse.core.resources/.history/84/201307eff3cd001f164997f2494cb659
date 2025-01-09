package utility;

public class TestDataPropertyFile {

	String testRailFilePath = "src/test/resources/TestRailData.properties";

	PropertyFileMethods propertyFileMethods = new PropertyFileMethods();

	public String getTestRunID() {
		return propertyFileMethods.readTestDataFromPropertyFile(testRailFilePath, "TEST_RUN_ID");
	}

	public String getTestRailUserName() {
		return propertyFileMethods.readTestDataFromPropertyFile(testRailFilePath, "TESTRAIL_USERNAME");
	}

	public String getTestRailPassword() {
		return propertyFileMethods.readTestDataFromPropertyFile(testRailFilePath, "TESTRAIL_PASSWORD");
	}

	public String getTestRailURL() {
		return propertyFileMethods.readTestDataFromPropertyFile(testRailFilePath, "RAILS_ENGINE_URL");
	}

	public String getTestRailPassStatus() {
		return propertyFileMethods.readTestDataFromPropertyFile(testRailFilePath, "TEST_CASE_PASSED_STATUS");
	}

	public String getTestRailFailStatus() {
		return propertyFileMethods.readTestDataFromPropertyFile(testRailFilePath, "TEST_CASE_FAILED_STATUS");
	}

	public String getTestRailEnableStatus() {
		return propertyFileMethods.readTestDataFromPropertyFile(testRailFilePath, "IS_ENABLED");
	}

	public String getTestRailDataFilePath() {
		return propertyFileMethods.readTestDataFromPropertyFile(testRailFilePath, "testRailDataFilePath");
	}

}