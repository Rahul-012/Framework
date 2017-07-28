package com.smartfactory.testcase;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.actions.WebAction;
import com.framework.testbase.TestBase;
import com.smartfactory.excelreader.Excel_Reader;
import com.smartfactory.pages.Login_Page;

public class NewTest extends TestBase{
	
	
	

	public NewTest()
	{
		super();
		
	}
	
	public Object[][] getData(String excelName,String sheetName){
		Excel_Reader data=new Excel_Reader(System.getProperty("user.dir") + "\\src\\main\\resources\\"+ excelName);
	   
		System.out.println("Read Data form get data Method");
		int rowNum=data.getRowCount(sheetName);
		System.out.println("NUmber of Rows" +rowNum);
		int colNum=data.getColumnCount(sheetName);
		System.out.println(colNum);
		
		Object sampledata[][]=new Object[rowNum-1][colNum];
		for(int i= 2; i < rowNum; i++){
			for(int j=0; j < colNum;j++){
				   sampledata[i-2][j] = data.getCellData(sheetName,j,i);
			}
		}
		
	//	sampledata[1][1]=data.getCellData("Sheet1", 1,1);
		return sampledata;
	
	}
	
	@DataProvider
	public Object[][] loginData()
	{
	  Object[][] logindta=	getData("testdata.xls","Sheet1");
	  return logindta;
		
		
		/*Object [][] facebookdata=new Object[2][2];
		 
		 
		 
		// Enter data to row 0 column 0
		facebookdata[0][0]="Selenium1@gmail.com";
		 
		 
		 
		// Enter data to row 0 column 1
		facebookdata[0][1]="Password1";
		 
		 
		 
		// Enter data to row 1 column 0
		facebookdata[1][0]="Selenium2@gmail.com";
		 
		// Enter data to row 1 column 0
		facebookdata[1][1]="Password2";
		 
		// return arrayobject to testscript
		return facebookdata;*/
	}
	
	

	@Test(dataProvider="loginData")
    public void  loginToApplication(String username,String password) throws Exception {
	
		signIn.login(username,password);
	  
  }
  
}
