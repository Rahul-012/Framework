package com.smartfactory.excelreader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.smartfactory.result.Result;


public class Excel_Reader {
	 
	public String path;
	static FileInputStream fis;
	static   HSSFWorkbook workbook;
	static   HSSFSheet sheet;
	static  	HSSFRow row;
	 	HSSFCell cell;
	  FileOutputStream fileOut;
	  public FileOutputStream fos = null;
	  private WebDriver web;
	  
	 public Excel_Reader(String path)
	   {
		   this.path=path;
		   try{
			   fis=new FileInputStream(path);
			   workbook=new HSSFWorkbook(fis);
			   fis.close();
		
			
		   }
		   catch(Exception e){
			   e.printStackTrace();
		   }
	   }
       
	
	public String getCellData(String sheetname,String colName,int rowNum){
		 
		int index;
		int col_Num=0;
		try{
			
			
			
		 index=workbook.getSheetIndex(sheetname);
		     sheet=workbook.getSheetAt(index);
		       row = sheet.getRow(0);
		     
		   for(int i=0; i < row.getLastCellNum(); i++){
		    	 if(row.getCell(i).getStringCellValue().equals(colName))
		    	 {
		    		 col_Num=i;
		    		 System.out.println(col_Num);
		    	 }
		     }
		     row = sheet.getRow(rowNum-1);
		     cell = row.getCell(col_Num);
		     
		     if(cell.getCellType()== cell.CELL_TYPE_STRING)
		    	 return cell.getStringCellValue();
		    else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)
		    	 return String.valueOf(cell.getStringCellValue());
		     else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
		    	 return "";
			
		}
	
			catch (Exception e) {
             e.printStackTrace();
				
			}
		
		 return null;
	 }
	 
       
	   public int getRowCount(String sheetname){
		 
		 int   index= workbook.getSheetIndex(sheetname);
		 if(index==-1)
			 return 0;
		 else{
		        sheet=workbook.getSheetAt(index);
			   int number =sheet.getLastRowNum()+1;
			   return number;
		 }
	   }
	   
	   public int getColumnCount(String sheetname){
		   int   index= workbook.getSheetIndex(sheetname);
			 if(index==-1)
				 return 0;
			 else
			 {
				sheet=  workbook.getSheet(sheetname);
				row  = sheet.getRow(0);
			    return row.getLastCellNum();
				
			 }
	   }
	   
	   
	   public String getCellData(String sheetname,int colName,int rowNum)
		{
			int index;
		//	int col_Num=0;
			try
			{
				
				
				
				
				index=workbook.getSheetIndex(sheetname);
				 sheet= workbook.getSheetAt(index);		
			   
				 
				  row= sheet.getRow(0);
				  
				 
				  
				  System.out.println("Row is "+row);
				
				 row= sheet.getRow(rowNum-1);
				 cell= row.getCell(colName);

				 
				 if(cell.getCellTypeEnum()==CellType.STRING)
				 {
					 return cell.getStringCellValue();
				 }
				 else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
					 return cell.getStringCellValue();
			
				 else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
					 return "";
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return null;
		
	 
	 
		}
	   
	   public  boolean setCellData(String sheetName, String colName, int rowNum, String value)
	    {
	        try
	        {
	            int col_Num = -1;
	            sheet = workbook.getSheet(sheetName);
	 
	            row = sheet.getRow(0);
	            for (int i = 0; i < row.getLastCellNum(); i++) {
	                if (row.getCell(i).getStringCellValue().trim().equals(colName))
	                {
	                    col_Num = i;
	                }
	            }
	 
	            sheet.autoSizeColumn(col_Num);
	            row = sheet.getRow(rowNum - 1);
	            if(row==null)
	                row = sheet.createRow(rowNum - 1);
	 
	            cell = row.getCell(col_Num);
	            if(cell == null)
	                cell = row.createCell(col_Num);
	 
	            cell.setCellValue(value);
	 
	            fos = new FileOutputStream(path);
	            workbook.write(fos);
	            fos.close();
	        }
	        catch (Exception ex)
	        {
	            ex.printStackTrace();
	            return  false;
	        }
	        return true;
	    }

	   public void test() throws IOException
	   {
		  int i=2;
		  String path=System.getProperty("user.dir")+"\\src\\main\\resources\\testdata.xls";
		  
		  fis = new FileInputStream(path);
	        // Access the required test data sheet
		  workbook = new HSSFWorkbook(fis);
	      
	         sheet = workbook.getSheet("Sheet1");
		   for(int count = 1; count <=sheet.getLastRowNum();count++){
	             row = sheet.getRow(count);
	            System.out.println("Running test case " + row.getCell(0).toString());
	            // Run the test for the current test data row
	       //   runTest(row.getCell(1).toString(),row.getCell(2).toString());
	          
	         setCellData("Sheet1","Expected Result",i,"AS EXPECTED");
	         i++;
	        }
	        fis.close();
	   }



	 
	/*public static void main(String[] args) throws IOException {
		  int i=2;
		String path=System.getProperty("user.dir")+"\\src\\main\\resources\\testdata.xls";
		Excel_Reader obj=new Excel_Reader(path);
		System.out.println(obj.getCellData("Sheet1",1, 2));
		
		// fis = new FileInputStream(path);
	        // Access the required test data sheet
		//  workbook = new HSSFWorkbook(fis);
	      
	       //  sheet = workbook.getSheet("Sheet1");
	//	   for(int count = 1; count <=sheet.getLastRowNum();count++){
	        //     row = sheet.getRow(count);
	          //  System.out.println("Running test case " + row.getCell(0).toString());
	            // Run the test for the current test data row
	          //  runTest(row.getCell(1).toString(),row.getCell(2).toString());
	          
	      // obj.setCellData("Sheet1","Status",i,"AS EXPECTED");
	      //   i++;
	     //   }
	      //  fis.close();
		obj.test();
		obj.setCellData("Sheet1","Status",i,"Pass");
		
		//obj.setCellData("Sheet1", "Stautus", 2, "value");
		
	//	obj.setCellData("Sheet1", "Status", 1, "Pass");
		System.out.println(obj.getRowCount("Sheet1"));
		System.out.println(obj.getColumnCount("Sheet1"));
		
	}*/
	   
	   public  void runTest(String strSearchString, String strPageTitle) {
	        
		     WebElement element;
		 
		        // Enter the search string and send it
		        String actualResult = web.findElement(By.className("pull-left")).getText();
		        System.out.println("Actual result"+actualResult);
		       
		       
		     //  element.submit();
		         // String s= driver.getTitle();
		         
		        // Check the title of the page
		        if (actualResult.equals(strPageTitle)) {
		            System.out.println("Page title is " + strPageTitle + ", as expected");
		        } else {
		            System.out.println("Expected page title was " + strPageTitle + ", but was " + web.getTitle() + " instead");
		        }
		         
		        //Close the browser
		      
		}
}

