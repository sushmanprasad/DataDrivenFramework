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
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void openAccountTest(String customer, String currency)
	{
		
	}	
}
