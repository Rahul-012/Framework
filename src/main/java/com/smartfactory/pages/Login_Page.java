package com.smartfactory.pages;

import java.io.FileOutputStream;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
	

	public void login() throws Exception{
		
	 
	    
		//action.inputText(obj.usernme,usrname);
	//    driver.findElement(By.name("email")).sendKeys(usrname);
	    
	 //   System.out.println(usrname);
		
	  //  CommonFunctionLib.log(driver,"Enter username");
		
	//	action.inputText(obj.passwrd,password);
	//	CommonFunctionLib.log(driver, "Enter Password");
		
	//	action.click(obj.btnlogin);
	//	CommonFunctionLib.log(driver,"Click on Login Button");
		CommonFunctionLib.sleep(5);
		
		//return new Login_Page(driver,action);
		
		  driver.findElement(By.id("gbqfq")).clear();
		  driver.findElement(By.id("gbqfq")).sendKeys("Testing");
		  driver.findElement(By.id("gbqfq")).sendKeys(Keys.ENTER);
		  driver.findElement(By.linkText("Software testing - Wikipedia, the free encyclopedia")).click();
		  String s = driver.getTitle();
		  writereport(s);

	}
	
	 public void writereport(String text) 
     { 
      try
      {
     FileOutputStream f = new FileOutputStream("c:\\Test\\output.xls",true);
     WritableWorkbook book = Workbook.createWorkbook(f); 
     WritableSheet sheet = book.createSheet("output", 0);
     Label l = new Label(0, 0, text);
     sheet.addCell(l);
     book.write(); 
     book.close(); 
      }
      catch (Exception e)
      {
       e.printStackTrace();
      }
      }

}
