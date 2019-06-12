package com.XXX.TestCases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.XXX.Base.TestBase;
import com.XXX.Utilities.TestUtil;

public class AddCustomerTest extends TestBase
{
	@Test(dataProviderClass=TestUtil.class, dataProvider="testData")
	public void addCustomerTest(Hashtable<String, String> data) throws InterruptedException
	{
		if(!data.get("RunMode").equals("Y")){
			
			throw new SkipException("Skipping the test case as the Run mode for data set is NO");
		}
		Thread.sleep(3000);
		//driver.findElement(By.cssSelector(OR.getProperty("addCustBtn_CSS"))).click();
		click("addCustBtn_XPATH");
		Thread.sleep(2000);
		//driver.findElement(By.cssSelector(OR.getProperty("firstName"))).sendKeys(firstName);
		type("firstName_CSS", data.get("firstName"));
		Thread.sleep(2000);
		//driver.findElement(By.cssSelector(OR.getProperty("lastName"))).sendKeys(lastName);
		type("lastName_CSS", data.get("lastName"));
		Thread.sleep(2000);
		//driver.findElement(By.cssSelector(OR.getProperty("postCode"))).sendKeys(postCode);
		type("postCode_CSS", data.get("postCode"));
		Thread.sleep(2000);
		//driver.findElement(By.cssSelector(OR.getProperty("addBtn_CSS"))).click();
		click("addBtn_CSS");
		
		Thread.sleep(2000);
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
		alert.accept();
		Thread.sleep(2000);
		//Assert.fail("Customer not added successfully");
	}
	
}
