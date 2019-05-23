package com.XXX.TestCases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.XXX.Base.TestBase;
import com.XXX.Utilities.TestUtil;

public class OpenAccountTest extends TestBase
{
	@Test(dataProviderClass=TestUtil.class,dataProvider="testData")
	public void openAccountTest(String customer, String currency)
	{
		click("openAccountBtn_XPATH");
		select("customerName_ID", "Harry Potter");
		select("currency_ID", "Dollar");
		click("process_XPATH");
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		//Assert.assertTrue(alert.getText().contains(alertText));
		alert.accept();
	}	
}
