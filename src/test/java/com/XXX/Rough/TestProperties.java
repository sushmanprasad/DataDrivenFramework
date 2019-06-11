package com.XXX.Rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties 
{
	public static void main(String[] args) throws FileNotFoundException, IOException 
	{
		//print the current system's user directory
		System.out.println(System.getProperty("user.dir"));
		//instantiate Properties for both OR and COnfig files
		Properties Config=new Properties();
		Properties OR=new Properties();
		//Open the properties files input stream
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\Config.properties");
		//load the properties files
		Config.load(fis);
		
		fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\OR.properties");
		OR.load(fis);
		//read the corresponding value of the key from properties file
		System.out.println(Config.getProperty("browser"));
		System.out.println(OR.getProperty("bnkMgrLgnBtn_XPATH"));
		//When reading property file for object locators from test case, it should be like following
		//driver.findElement(By.cssSelector(OR.getProperty("bnkMgrLgnBtn"))).click();
		
	}
}
