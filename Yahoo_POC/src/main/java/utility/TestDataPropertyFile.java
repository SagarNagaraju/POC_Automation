package utility;

import utility.PropertyFileMethods;

public class TestDataPropertyFile {

	String filePath = "src/test/resources/TestData.properties";

	PropertyFileMethods propertyFileMethods = new PropertyFileMethods();


	public String getSearchStock() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "searchStock");
	}

	public String getStockName() {
		return propertyFileMethods.readTestDataFromPropertyFile(filePath, "stockName");
	}

}