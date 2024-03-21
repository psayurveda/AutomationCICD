package dublinusdpfc.pageobjects;

//import org.apache.poi.ss.usermodel.*;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//File Operations like Creating, closing, and writing the file
public class ExcelFileOperations {
	    public static String filepath; 
	    public static XSSFWorkbook workbook ; //from apache POI (Poor Obfuscation Implementation) for Ms office
	    public static XSSFSheet sheet;        //high level spreadsheet representation
	    public static FileOutputStream outputStream=null; //
	    
	public static void createFile()  {
		try {
			filepath = "/Users/ashishumrani/Documents/ParentFaculty.xlsx";
			FileInputStream fis = new FileInputStream(new File(filepath));
			outputStream = new FileOutputStream(filepath);
			workbook = new XSSFWorkbook();
		} catch (Exception ex) {
			System.out.println("Exeption "+ex.getMessage());
		}

	}
	    
	public static void fileWrite(List<WebElement> members, String school) {
		//FileOutputStream outputStream=null;
		try {
		//XSSFWorkbook workbook = new XSSFWorkbook();
		//XSSFSheet sheet = workbook.createSheet(school);
		sheet = workbook.createSheet(school);
		int rowNum =0;
		String[] info ;
		System.out.println("List of members "+members.size());
		for (WebElement data : members) {
			XSSFRow row = sheet.createRow(rowNum++);
			XSSFCell cell = row.createCell(0);
			//cell.setCellValue(data.getText());
			if (data.getText().trim() != null) {
				System.out.println("Data "+data.getText());
				info=data.getText().split("-");
				cell.setCellValue(info[0]);
				cell = row.createCell(1);
				cell.setCellValue(info[1]);
				cell = row.createCell(2);
				cell.setCellValue(info[2]);
				System.out.println("Values "+info[0]+ " "+ info[1]+" "+info[2] );
				}
			}

			//Write the workbook to an Excel file
			// outputStream = new FileOutputStream(filepath);
			workbook.write(outputStream);
			//outputStream.close();
			//workbook.close();
		} catch (Exception ex) {
			System.out.println("Exception "+ex.getMessage());
		} 
	}
	public static void fileWrite(List<String> members, String school, String separator) {
		String[] info ;
		try {
			int rowNum =1;
			
			XSSFSheet sheet = workbook.createSheet(school);
			XSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("Name");
			row.createCell(1).setCellValue("Role");
			row.createCell(2).setCellValue("Email");
		
			for (String data : members) {
				System.out.println(" From File "+data);
				row = sheet.createRow(rowNum++);
				info = data.split(separator);
			
				for (int col=0;col<info.length; col++) {
				
					row.createCell(col).setCellValue(info[col]);
				
				}

			}

			//Write the workbook to an Excel file
			outputStream = new FileOutputStream(filepath);
			workbook.write(outputStream);
		 } catch (Exception ex) {
			System.out.println("Exception "+ex.getMessage());
		} 
	}

	public static void closeFile() {
		try {
			outputStream.close();
			workbook.close();
		} catch (Exception ex) {
			System.out.println("Closing files");
		}
		
	}
}