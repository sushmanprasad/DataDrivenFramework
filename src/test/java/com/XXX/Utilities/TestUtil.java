package com.XXX.Utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.XXX.Base.TestBase;

public class TestUtil extends TestBase 
{

	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() throws IOException
	{

		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		//following is for running through maven
		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));
		//following is for running through testng
		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\target\\test-output\\html\\" + screenshotName));

	}
	@DataProvider(name="testData")
	public Object[][] getData(Method m)
	{
		//Since excel sheetname is the same as the classname, we do the following to extract the classname
		//The following is now not required as we are getting the method name and using directly
		/*String packageClassName=(this.getClass().getName());
		String[] splitPackageClassName=packageClassName.split("\\.");//   "AddCustomerTest";
		int number=splitPackageClassName.length;
		String sheetName=splitPackageClassName[number-1];*/
		String sheetName=m.getName();
		//String sheetName="AddCustomerTest";
		int rows=excel.getRowCount(sheetName);
		int cols=excel.getColumnCount(sheetName);
		Object[][] data=new Object[rows-1][cols];
		for(int rowNum=2;rowNum<=rows;rowNum++)
		{
			for(int colNum=0;colNum<cols;colNum++)
			{
				data[rowNum-2][colNum]=excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		return data;
	}

}
