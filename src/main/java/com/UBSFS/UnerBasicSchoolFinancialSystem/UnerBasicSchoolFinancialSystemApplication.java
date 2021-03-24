package com.UBSFS.UnerBasicSchoolFinancialSystem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UnerBasicSchoolFinancialSystemApplication {
  public  static String sys=System.getProperty("user.dir");
      public static String getPath=sys +"/src/main/java/data/Book1.xlsx";
      private static DataTrial DT;
    //  C:\Users\enock\Documents\NetBeansProjects\UnerBasicFinancialSystem\src\main\java\data\Book1.xlsx
	public static void main(String[] args) throws IOException {
             getCellData();
		SpringApplication.run(UnerBasicSchoolFinancialSystemApplication.class, args);
	
      
        }
        public static void getCellData() throws IOException{
            DataFormatter df=new DataFormatter();
            XSSFWorkbook  workbook= new XSSFWorkbook((getPath));
         //   System.out.print("............"+workbook.getNumberOfSheets()+workbook.getSheetName(0)+ "  . ,,,,,,,,,,,,,,,,");
            XSSFSheet sheet =workbook.getSheetAt(0);
            
            List <DataTrial>Data=new ArrayList<>();
            for(Row row:sheet){
             //  System.out.print("row numbers"+row.getLastCellNum());
               DT=new DataTrial();
             String CellValue=df.formatCellValue(row.getCell(0));
            DT.setName(row.getCell(0).getStringCellValue());
             DT.setAge(df.formatCellValue(row.getCell(1)));
             DT.setSex((String)row.getCell(2).getStringCellValue());
          //  System.out.print(CellValue + "\t");
            Data.add(DT); 
            }
            
            
           System.out.println();
            
            for(DataTrial dt:Data){
                if(dt.getName().equals("Kofi")){
            System.out.println(" MyCellData------------ "+dt.getName()+"-"+dt.getAge()+"-"+dt.getSex());
        }
            }
        }
}
