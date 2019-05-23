package com.XXX.TestCases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.XXX.Base.TestBase;
import com.XXX.Utilities.TestUtil;

public class AddCustomerTest extends TestBase
{
	@Test(dataProviderClass=TestUtil.class, dataProvider="testData")
	public void addCustomerTest(String firstName, String lastName, String postCode, String alertText) throws InterruptedException
	{
		//driver.findElement(By.cssSelector(OR.getProperty("addCustBtn_CSS"))).click();
		click("addCustBtn_XPATH");
		Thread.sleep(2000);
		//driver.findElement(By.cssSelector(OR.getProperty("firstName"))).sendKeys(firstName);
		type("firstName_CSS", firstName);
		Thread.sleep(2000);
		//driver.findElement(By.cssSelector(OR.getProperty("lastName"))).sendKeys(lastName);
		type("lastName_CSS", lastName);
		Thread.sleep(2000);
		//driver.findElement(By.cssSelector(OR.getProperty("postCode"))).sendKeys(postCode);
		type("postCode_CSS", postCode);
		Thread.sleep(2000);
		//driver.findElement(By.cssSelector(OR.getProperty("addBtn_CSS"))).click();
		click("addBtn_CSS");
		
		Thread.sleep(2000);
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		//Assert.fail("Customer not added successfully");
	}
	
}
