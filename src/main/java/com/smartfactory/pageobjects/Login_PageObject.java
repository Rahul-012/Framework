package com.smartfactory.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_PageObject {
	
      public Login_PageObject(WebDriver driver)
      {
    	  PageFactory.initElements(driver, this);
      }
      
      @FindBy(name="email")
      public WebElement usernme;
      
      @FindBy(name="password")
      public WebElement passwrd;
      
      @FindBy(className="loginbtn")
      public WebElement btnlogin;
}
