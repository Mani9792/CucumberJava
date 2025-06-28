package com.kaiser.cucumberJava;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader	{
	
	public static List<Map<String,String>> getExcelDataOneRow(String path, String sheetName)
	{	
	List<Map<String, String>> list = new ArrayList<>();
	
	DataFormatter formatter = new DataFormatter();
	
	try 
	{
		FileInputStream input =  new FileInputStream(path);
		
		XSSFWorkbook workbook = new XSSFWorkbook(input);
		
		XSSFSheet testDataSheet = workbook.getSheet(sheetName);
		
		int dataRowCount = testDataSheet.getLastRowNum()-testDataSheet.getFirstRowNum();
		
		Row row = testDataSheet.getRow(0);
		
		//Getting headings			
		int headingColCount = row.getPhysicalNumberOfCells();
		
		String[] headings = new String[headingColCount];
		
		for(int cellCount=0; cellCount< row.getLastCellNum();cellCount++)
		{
			headings[cellCount] = row.getCell(cellCount).getStringCellValue();
		}
		
		//Getting elements
		for(int rowCount=1; rowCount <= dataRowCount; rowCount++)
		{
			Map<String, String> testCaseMap = new HashMap<String, String>();
			
			Row dataRow = testDataSheet.getRow(rowCount);
			
			for(int cellCount = 0; cellCount< dataRow.getLastCellNum();cellCount++)
			{
				testCaseMap.put(headings[cellCount], formatter.formatCellValue(testDataSheet.getRow(rowCount).getCell(cellCount)));
			}
			list.add(testCaseMap);
		}
		System.out.println(list);
		workbook.close();

	}
	
	catch (Exception e)
	{
		e.printStackTrace();
	}
	
	return list;
	}
}
