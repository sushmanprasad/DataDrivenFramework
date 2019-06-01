package com.XXX.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.XXX.Utilities.ExcelReader;
import com.XXX.Utilities.ExtentManager;
import com.XXX.Utilities.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase 
{
	/* WebDriver - done
	 * DB
	 * Excel
	 * Logs - log4j jar file, .log files in resources, log4j.properties, logger class
	 * Mail
	 * Properties - done
	 * ReportNG, ExtentReports to create customized reports
	 * Jenkins
	 */
	public static WebDriver driver;
	public static Properties Config=new Properties();
	public static Properties OR=new Properties();
	public static FileInputStream fis;
	public static Logger log=Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\DataDrive\\testData.xlsx");
	public static WebDriverWait wait;
	public ExtentReports extentReport=ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	@BeforeSuite
	public void setup()
	{
		if (driver==null)
		{
			try 
			{
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\Config.properties");
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			try 
			{
				Config.load(fis);
				log.debug("Config file loaded!");
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			try 
			{
				fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\OR.properties");
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			try 
			{
				OR.load(fis);
				log.debug("OR file loaded!");
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty())
			{
				browser=System.getenv("browser");
			}
			else
			{
				browser=Config.getProperty("browser");
			}
			Config.setProperty("browser", browser);
			if((Config.getProperty("browser")).equals("Firefox"))
			{
				System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir")+"\\src\\test\\resources\\Executables\\geckodriver.exe");
				driver=new FirefoxDriver();
			}
			else if((Config.getProperty("browser")).equals("Chrome"))
			{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\Executables\\chromedriver.exe");
				driver=new ChromeDriver();
				log.debug("Chrome launched!");
			}
			else if((Config.getProperty("browser")).equals("IE"))
			{
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\Executables\\MicrosoftWebDriver.exe");
				driver=new InternetExplorerDriver();
			}
			driver.get(Config.getProperty("testSiteUrl"));
			log.debug("Navigated to "+Config.getProperty("testSiteUrl")+"!");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")), TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(Config.getProperty("pageload.timeout")), TimeUnit.SECONDS);
			wait=new WebDriverWait(driver, 5);
		}
	}
	public void click(String locator)
	{
		if (locator.endsWith("_CSS"))
		{
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		}
		else if (locator.endsWith("_XPATH"))
		{
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		}
		else if (locator.endsWith("_ID"))
		{
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		test.log(LogStatus.INFO, "Clicking on : "+locator);
	}
	public void type(String locator, String value)
	{
		if (locator.endsWith("_CSS"))
		{
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		}
		else if (locator.endsWith("_XPATH"))
		{
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		}
		else if (locator.endsWith("_ID"))
		{
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}
		test.log(LogStatus.INFO, "Typing on : "+locator+" entered value as "+value);
	}
	static WebElement dropdown;
	public void select(String locator, String value)
	{
		if (locator.endsWith("_CSS"))
		{
			dropdown=driver.findElement(By.cssSelector(OR.getProperty(locator)));
		}
		else if (locator.endsWith("_XPATH"))
		{
			dropdown=driver.findElement(By.xpath(OR.getProperty(locator)));
		}
		else if (locator.endsWith("_ID"))
		{
			dropdown=driver.findElement(By.id(OR.getProperty(locator)));
		}
		Select select=new Select(dropdown);
		select.selectByVisibleText(value);
		test.log(LogStatus.INFO, "Selecting from dropdown : "+locator+" value as "+value);
	}
	public boolean isElementPresent(By by)
	{
		try
		{
			driver.findElement(by);
			return true;
		}
		catch(NoSuchElementException e)
		{
			System.out.println(e);
			return false;
		}
	}
	public static void verifyEquals(String expected, String actual) throws IOException
	{
		try
		{
			Assert.assertEquals(actual, expected);
		}
		catch(Throwable t)
		{
			TestUtil.captureScreenshot();
			//ReportNG
			Reporter.log("<br>"+"Verification failure: "+t.getMessage()+"<br>");
			Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			//Extent Reports
			test.log(LogStatus.FAIL, " Verification Failed with exception : "+t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		}
	}
	@AfterSuite
	public void tearDown()
	{
		if (driver!=null)
		{
			driver.quit();
		}
		log.debug("Test Execution completed!");
	}
}
