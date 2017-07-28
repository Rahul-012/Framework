package com.functions;

import org.openqa.selenium.WebDriver;

public class CommonFunctionLib {
      
	public WebDriver driver;
	
	public CommonFunctionLib(WebDriver driver)
	{
		this.driver=driver;
	}

	public static void sleep(int secounds) {
		try {
			Thread.sleep(secounds * 1000);
		} catch (InterruptedException e) {
		}
	}
	
	public static void  log(WebDriver driver,String log){
		      if(driver!=null)
		      {
		    	  System.out.println(driver + " : " +log);
		      }
	}
}
