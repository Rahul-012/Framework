package com.smartfactory.testcase;

import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.actions.WebAction;
import com.framework.testbase.TestBase;
import com.smartfactory.excelreader.Excel_Reader;
import com.smartfactory.pages.Login_Page;

public class NewTest extends TestBase{
	

   
String path=System.getProperty("user.dir")+"\\src\\main\\resources\\testdata.xls";
	

	/*public NewTest()
	{
		super();
		
	}*/
	
	public Object[][] getData(String excelName,String sheetName){
		Excel_Reader data=new Excel_Reader(System.getProperty("user.dir") + "\\src\\main\\resources\\"+ excelName);
	   
		System.out.println("Read Data form get data Method");
		int rowNum=data.getRowCount(sheetName);
		System.out.println("NUmber of Rows" +rowNum);
		int colNum=data.getColumnCount(sheetName);
		System.out.println("Number of Columns "+colNum);
		
		Object sampledata[][]=new Object[rowNum-1][colNum];
		for(int i= 2; i <= rowNum; i++){
			System.out.println("i=" + i);
			for(int j=0; j <colNum; j++){
				
				   sampledata[i-2][j] = data.getCellData(sheetName,j,i);
				   System.out.println("j"+j); 
			}
		}
		
	//	sampledata[1][1]=data.getCellData("Sheet1", 1,1);
		return sampledata;
	
	}
	
	@DataProvider
	public Object[][] loginData()
	{
	  Object[][] logindta=getData("testdata.xls","Sheet1");
	  return logindta;
		
	}
	
	

    @Test(dataProvider="loginData")
    public void   loginToApplication(String username,String password,String runMode) throws Exception {
   
    	if(runMode.equals("N")) {
			throw new SkipException("Skipped Test case is");
		}

    	
    	 Excel_Reader 	excel=new Excel_Reader(path);
    	signIn.login(username,password);
    	excel.test();
    //	excel.setCellData("Sheet1", "Status",i,"This is Test");
    	
    	
	  
  }
  
}
