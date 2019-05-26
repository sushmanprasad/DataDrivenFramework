package com.XXX.TestCases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.XXX.Base.TestBase;
import com.XXX.Utilities.TestUtil;

public class OpenAccountTest extends TestBase
{
	@Test(dataProviderClass=TestUtil.class,dataProvider="testData")
	public void openAccountTest(Hashtable<String, String> data)
	{
		if(!TestUtil.isTestRunnable("openAccountTest", excel))
		{
			throw new SkipException("Skipping the test " + "openAccountTest".toUpperCase() + " as the run mode is set to NO");
		}
		click("openAccountBtn_XPATH");
		select("customerName_ID", data.get("customer"));
		select("currency_ID", data.get("currency"));
		click("process_XPATH");
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alertText")));
		alert.accept();
	}	
}
