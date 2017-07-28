package com.framework.actions;

import java.util.NoSuchElementException;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebAction {
	
	private WebDriver driver=null;
	protected WebDriver webDriver = null;
	final int waitvalue=10;
	final int timeout=10;
	
	public static final int CLICK_TIMEOUT_SECONDS = 10;
	
	/**
	 * Constructor to initialize WebAction class objects
	 * 
	 * @param driver
	 */
	
    public WebAction(WebDriver driver)
    {
		this.driver=driver;
    	PageFactory.initElements(driver,this);
	}
    
    
    public WebElement clearText(WebElement el) throws Exception
    {
    	String tag=el.getTagName();
    	
    	try
    	{
    		waitUntilClickable(el, CLICK_TIMEOUT_SECONDS);
    		el.clear();
    	}
    	catch(Exception e)
    	{
    		throw new Exception(
					"WebAction -> clearText(WebElement el) - Error clearing text from element <%s>: %s" + tag
							+ e.getMessage());
    	}
    	
    	return el;
    }
    
    public boolean isClickable(WebElement el)
    {
    	if(el==null)
    	{
    		return false;
    	}
    	try
    	{
    		 if(!el.isDisplayed())
    			 return false;
    		 if(!el.isEnabled())
    			 return false;
    		 if(el.getSize().getHeight() <= 0 || el.getSize().getWidth() <= 0) // If
																				// width
																				// or
																				// height
																				// is
																				// 0,
																				// element
																				// is
																				// not
																				// cl
    			 return false;
    	}
    	catch(Exception e)
    	{
    		
    		 return false;
    	}
    	return true;
    }
    
    public WebElement waitUntilClickable(WebElement el,int timeout)
    {
    	int waitSeconds = timeout;
		final String message = "Element never became clickable after '%d' seconds" + waitSeconds;
		WebDriverWait wait = new WebDriverWait(driver, waitSeconds);
		wait.withMessage(message).ignoring(StaleElementReferenceException.class);
		wait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver webDriver) {
				if (isClickable(el)) {
					return el;
				}
				return null;
			}
		});
		return el;
    }
    
      public void inputText(WebElement el,String value) throws Exception{
    	  String tag=el.getTagName();
    	  try{
    		  isClickable(el);
    		  clearText(el);
    		  el.sendKeys(value);
    	  }
    	  catch(Exception e){
    		  throw new Exception(
  					"WebAction -> inputText(WebElement el, String value) - Error entering text into element <%s>: %s"
  							+ tag + e.getMessage());
    		
    	  }
      }
      
      public WebElement click(WebElement el) throws Exception
      {
    	  String tag=el.getTagName();
    	  try{
    		  waitUntilClickable(el, CLICK_TIMEOUT_SECONDS);
    		  el.click();
    	  }
    	  catch(NoSuchElementException e){
    		  throw new Exception(
  					"WebAction -> click() - Error in click operation on element <%s>: %s" + tag + e.getMessage());
    	  }
    	  return el;
      }
    
}
