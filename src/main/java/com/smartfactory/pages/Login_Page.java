package com.smartfactory.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.framework.actions.WebAction;
import com.functions.CommonFunctionLib;
import com.smartfactory.pageobjects.Login_PageObject;

public class Login_Page {
	
	protected WebDriver driver;
	protected WebAction action;
	private Login_PageObject obj;
	
	public Login_Page(WebDriver driver, WebAction action){
		this.driver=driver;
		this.action=action;
		obj=new Login_PageObject(driver);
	}
	

	public void login(String usrname,String password) throws Exception{
		
	 
	  //   driver.findElement(By.name("email")).sendKeys(usrname);
		CommonFunctionLib.log(driver,usrname);
	  action.inputText(obj.usernme,usrname);
	    CommonFunctionLib.log(driver,"Enter username");
	  //  driver.findElement(By.name("password")).sendKeys(password);
		
		action.inputText(obj.passwrd,password);
		CommonFunctionLib.log(driver, "Enter Password");
		System.out.println("Password is "+password);
		
	
		action.click(obj.btnlogin);
		CommonFunctionLib.log(driver,"Click on Login Button");
	//	CommonFunctionLib.sleep(5);
		
		//return new Login_Page(driver,action);
		
	}

}
