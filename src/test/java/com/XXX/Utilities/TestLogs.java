package com.XXX.Utilities;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TestLogs 
{
	public static Logger log=Logger.getLogger(TestLogs.class.getName());
	public static void main(String[] args)
	{
		Date d=new Date();
		System.out.println(d.toString().replace(":", "_").replace(" ", "_"));
		System.setProperty("current.date", d.toString().replace(":", "_").replace(" ", "_"));
		PropertyConfigurator.configure("./src/test/resources/Properties/log4j.properties");
		log.info("Started the logging");		
		log.error("Here the error logs will be printed");
	}
}
