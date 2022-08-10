package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;

public class ExcelFileUtil {

	XSSFWorkbook wb;
	//constructor for reading excel path
	public ExcelFileUtil(String excelpath) throws Throwable
	{
		FileInputStream fi = new FileInputStream(excelpath);
	wb = new XSSFWorkbook(fi);
		}
	//method for row count
	public int rowCount(String SheetName)
	{
		return wb.getSheet(SheetName).getLastRowNum();
		
	}
	//method for count no of cells in a row
	public int cellCount (String SheetName)
	{
		return wb.getSheet(SheetName).getRow(0).getLastCellNum();
		
			}
	//method for celll data 
	public String getCellData(String SheetName,int row,int column)
	{
		String data = " " ;
if(wb.getSheet(SheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
{
	int celldata = (int)wb.getSheet(SheetName).getRow(row).getCell(column).getNumericCellValue();
data = String.valueOf(celldata);
		return SheetName;
		
	}
else
{
	data = wb.getSheet(SheetName).getRow(row).getCell(column).getStringCellValue();
}
return data;
}

	//method for setcelldata
	public void setCellData(String SheetName,int row,int column,String status,String writeexcel) throws Throwable
{
		//get sheet from workbook
		XSSFSheet ws = wb.getSheet(SheetName);
		//get row from sheet
		XSSFRow rowNum = ws.getRow(row);
		//create cell
		XSSFCell cell = rowNum.createCell(column);
		//write status
		cell.setCellValue(status);
		if (status.equalsIgnoreCase("pass"))
				{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.BRIGHT_GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
			
				}
		else if (status.equalsIgnoreCase("Fail"))
			
			{ 
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			rowNum.getCell(column).setCellStyle(style);
				}
		 
	FileOutputStream fo = new FileOutputStream(writeexcel);
	wb.write(fo);
}

/*public static void main(String[]args )throws Throwable{
		ExcelFileUtil xl = new ExcelFileUtil("E:/Dummy.xlsx");
		//count no of rows
		int rc = xl.rowCount("login");
		//count no of columns
		int cc =xl.cellCount("Login");
		
		System.out.println(rc +" " +cc);
		for (int i =1;i<=rc;i++)
		{
			String user = xl.getCellData("login",i,0);
			String pass = xl.getCellData("login",i,1);
			System.out.println(user + " " +pass);
		xl.setCellData("login",i,2,"pass","E://Results.xlsx");
			//xl.setcelldata("login",i,2,"fail","E://Results.xlsx");
			//xl.setcelldata("login",i,2,"blocked","E://Results.xlsx"); 
		} */
			
	}
	
	