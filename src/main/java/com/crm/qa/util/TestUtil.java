package com.crm.qa.util;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;
import com.crm.qa.config.Xls_Reader;

public class TestUtil extends TestBase {
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	public static String newContactData = "C://Selenium_Workspace/POMTest/src/main/java/com/crm/qa/testdata/FreeCRMTestData.xlsx";
	Xls_Reader newContactDataFile = new Xls_Reader(newContactData);
	
	public void switchToFrame() {
		driver.switchTo().frame(0);
	}
		
public Object[][] getNewContactData(String sheet) {
	Object[][] data = newContactDataFile.getData(sheet); 
	return data;
}
public static void takeScreenshotAtEndOfTest() throws IOException {
	System.out.println("Taking Screenshot");
	File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	String currentDir = System.getProperty("user.dir");
	FileUtils.copyFile(srcFile, new File(currentDir + "\\screenshots\\" + System.currentTimeMillis() + ".jpg"));
}
	}

