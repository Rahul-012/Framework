package com.smartfactory.result;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Result {
	
	private static WebDriver driver;
	
public static void runTest(String strSearchString, String strPageTitle) {
        
     WebElement element;
 
        // Enter the search string and send it
        String actualResult = driver.findElement(By.className("pull-left")).getText();
        System.out.println("Actual result"+actualResult);
       
       
     //  element.submit();
         // String s= driver.getTitle();
         
        // Check the title of the page
        if (actualResult.equals(strPageTitle)) {
            System.out.println("Page title is " + strPageTitle + ", as expected");
        } else {
            System.out.println("Expected page title was " + strPageTitle + ", but was " + driver.getTitle() + " instead");
        }
         
        //Close the browser
      
}

}
