package com.framework.testbase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

import com.framework.actions.WebAction;
import com.framework.common.Config;
import com.smartfactory.pages.Login_Page;

public class TestBase {
	protected static WebDriver driver=null;
	protected static String url=null;
	protected static String browser=null;
	protected   WebAction action;
	protected   Login_Page signIn;
	
	@BeforeClass
	public  void setup()
	{
		
		browser=Config.browser;
		System.out.println(browser);
		url=Config.url;
		System.out.println(url);
	
		
		switch (browser)
		{
		case "chrome":
		//	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Binary\\chromedriver.exe");
			  System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver.exe");
			driver=new ChromeDriver();
			WebAction action = new WebAction(driver);
			signIn=new Login_Page(driver, action);
			
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver","C:\\Program Files\\geckodriver.exe");
			driver=new FirefoxDriver();
			break;
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.navigate().to(url);
	}

}
