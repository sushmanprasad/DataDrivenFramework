package com.XXX.TestCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.XXX.Base.TestBase;

public class BankManagerLoginTest extends TestBase
{
	@Test
	public void bankManagerLoginTest() throws IOException, InterruptedException
	{
		//Following is a hard assertion as the test will not execute any further the following statement, since the following statement assertion fails
		//Assert.assertEquals("abc", "xyz");
		//If Assert is surrounded by Try Catch, no failure will be reported
		//But this can be implemented as follows as a soft assertion
		verifyEquals("abc", "xyz");
		Thread.sleep(3000);
		log.debug("Inside login test!");
		//instead of writing code to click on button as following
		//driver.findElement(By.cssSelector(OR.getProperty("bnkMgrLgnBtn_CSS"))).click();
		//we use the following code to click
		Thread.sleep(3000);
		click("bnkMgrLgnBtn_XPATH");		
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("addCustBtn_XPATH"))),"Login not successful!");
		log.debug("Login successfully executed!");	
		//Assert.fail("Login not successful");
	}
}
