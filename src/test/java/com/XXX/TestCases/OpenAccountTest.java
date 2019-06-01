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
	public void openAccountTest(Hashtable<String, String> data) throws InterruptedException
	{
		if(!TestUtil.isTestRunnable("openAccountTest", excel))
		{
			throw new SkipException("Skipping the test " + "openAccountTest".toUpperCase() + " as the run mode is set to NO");
		}
		Thread.sleep(3000);
		click("openAccountBtn_XPATH");
		Thread.sleep(3000);
		select("customerName_ID", data.get("customer"));
		Thread.sleep(3000);
		select("currency_ID", data.get("currency"));
		Thread.sleep(3000);
		click("process_XPATH");
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alertText")));
		alert.accept();
	}	
}
