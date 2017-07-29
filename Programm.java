package demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;




import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;





import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;



public class Programm {
	
	public static  WebDriver driver;
	
	public  static FileInputStream fis = null;
    public static FileOutputStream fos = null;
    public static HSSFSheet sheet = null;
    public static  HSSFWorkbook wb = null;
  
    public static HSSFRow row = null;
    public  static HSSFCell cell = null;



public static void runTest(String strSearchString, String strPageTitle) {
        
        // Start a browser driver and navigate to Google
               System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver.exe");
		   driver=new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://www.google.com");
 
        // Enter the search string and send it
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys(strSearchString);
        element.submit();
         // String s= driver.getTitle();
         
        // Check the title of the page
        if (driver.getTitle().equals(strPageTitle)) {
            System.out.println("Page title is " + strPageTitle + ", as expected");
        } else {
            System.out.println("Expected page title was " + strPageTitle + ", but was " + driver.getTitle() + " instead");
        }
         
        //Close the browser
      
}
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		
		/*setUp();
		test();*/
		/*  System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver.exe");
		  driver=new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  driver.get("http://wikipedia.com");
		  Thread.sleep(4000);
		  String s = driver.getTitle();
		  System.out.println(s);
		  writereport(s);  */
		
		
		ExcelApiTest eat = new ExcelApiTest("C:\\Test\\output.xls");
		
    //    eat.setCellData("Credentials","Result",2,s);
		
		int i=2;
		
		 try {
		        // Open the Excel file
		         fis = new FileInputStream("C:\\Test\\output.xls");
		        // Access the required test data sheet
		         wb = new HSSFWorkbook(fis);
		      
		         sheet = wb.getSheet("Credentials");
		        // Loop through all rows in the sheet
		        // Start at row 1 as row 0 is our header row
		         
		        for(int count = 1; count <=sheet.getLastRowNum();count++){
		             row = sheet.getRow(count);
		            System.out.println("Running test case " + row.getCell(0).toString());
		            // Run the test for the current test data row
		            runTest(row.getCell(1).toString(),row.getCell(2).toString());
		         eat.setCellData("Credentials","Result",i,"AS EXPECTED");
		         i++;
		        }
		        fis.close();
		    } 
		 
		 catch (IOException e) {
		        System.out.println("Test data file not found");
		    } 
		
	}
	
	
	
	
	
	
}


