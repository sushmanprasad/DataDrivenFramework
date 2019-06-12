package com.XXX.Rough;
import org.testng.Assert;
//This class is written for practice. Was not present in the Udemy course
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

public class AssertSample 
{

	public static void main(String[] args) 
	{
		/*Assertion hardAssert=new Assertion();
		try
		{
			hardAssert.assertEquals("Try", "TRY");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Test passed");*/
		SoftAssert sa= new SoftAssert();
	    sa.assertTrue(5<1);
	    System.out.println("Assertion failed");
	    sa.assertFalse(1<5);
		System.out.println("Assertion Failed");
		sa.assertEquals("Passed", "Failed");
		System.out.println("Assertion Failed"); 
		sa.assertAll();
	}

}
