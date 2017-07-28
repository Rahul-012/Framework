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
		
	 
	    
		//action.inputText(obj.usernme,usrname);
	    driver.findElement(By.name("email")).sendKeys(usrname);
	    
	    System.out.println(usrname);
		
	    CommonFunctionLib.log(driver,"Enter username");
		
	//	action.inputText(obj.passwrd,password);
	//	CommonFunctionLib.log(driver, "Enter Password");
		
	//	action.click(obj.btnlogin);
	//	CommonFunctionLib.log(driver,"Click on Login Button");
		CommonFunctionLib.sleep(5);
		
		//return new Login_Page(driver,action);
		
	}

}
