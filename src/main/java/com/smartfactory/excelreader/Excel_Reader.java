package com.smartfactory.excelreader;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;


public class Excel_Reader {
	 
	public String path;
	FileInputStream fis;
	HSSFWorkbook workbook;
	HSSFSheet sheet;
	HSSFRow row;
	HSSFCell cell;
	FileOutputStream fileOut;
	  
	 public Excel_Reader(String path)
	   {
		   this.path=path;
		   try{
			   fis=new FileInputStream(path);
			   workbook=new HSSFWorkbook(fis);
		
			
		   }
		   catch(Exception e){
			   e.printStackTrace();
		   }
	   }
       
	
	public String getCellData(String sheetname,int colName,int rowNum){
		 
		try{
			int col_Num=0;
			
		int index=workbook.getSheetIndex(sheetname);
		     sheet=workbook.getSheetAt(index);
		       row = sheet.getRow(0);
		     
		     for(int i=0; i < row.getLastCellNum(); i++){
		    	 if(row.getCell(i).getStringCellValue().equals(colName)){
		    		 col_Num=i;
		    	 }
		     }
		     row = sheet.getRow(rowNum-1);
		     cell = row.getCell(col_Num);
		     
		     if(cell.getCellTypeEnum()== CellType.STRING)
		    	 return cell.getStringCellValue();
		     else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)
		    	 return String.valueOf(cell.getStringCellValue());
		     else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
		    	 return "";
			
		}
	
			catch (Exception e) {
             e.printStackTrace();
				return "row " + rowNum + " or column " + colName
						+ " does not exist  in xls";
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
	   
	   
	   /*public String getCellData(String sheetname,int colName,int rowNum)
		{
			int index;
			int col_Num=0;
			try
			{
				 index=workbook.getSheetIndex(sheetname);
				 sheet= workbook.getSheetAt(index);		
				  row= sheet.getRow(0);
				
				 row= sheet.getRow(rowNum-1);
				 cell= row.getCell(colName);
				 if(cell.getCellType()==Cell.CELL_TYPE_STRING)
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
		
	   
	 
		}*/
	   
}
	 
	 
/*	public static void main(String[] args) {
		String path=System.getProperty("user.dir")+"\\src\\main\\resources\\testdata.xls";
		Excel_Reader obj=new Excel_Reader(path);
		System.out.println(obj.getCellData("Sheet1", 1, 1));
	//	obj.setCellData("Sheet1", "Status", 1, "Pass");
		System.out.println(obj.getRowCount("Sheet1"));
		System.out.println(obj.getColumnCount("Sheet1"));
		
	}*/

