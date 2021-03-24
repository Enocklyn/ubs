/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSFS.UnerBasicSchoolFinancialSystem.Excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author enock
 */
@Service
public class StudentBillingFinanceExcelService {
 //public   
         
          public   XSSFWorkbook getCellData( MultipartFile file) throws IOException{
            DataFormatter df=new DataFormatter();
            XSSFWorkbook  workbook= new XSSFWorkbook((file.getInputStream()));
         //   System.out.print("............"+workbook.getNumberOfSheets()+workbook.getSheetName(0)+ "  . ,,,,,,,,,,,,,,,,");
          return workbook;}
          
          public List<String>ClassNames( MultipartFile getPath) throws IOException{
          List<String>names=new ArrayList<>();
            for(int a=0; a<=getCellData( getPath).getNumberOfSheets()-1;a++){
             names.add(getCellData( getPath).getSheetName(a));
            }  
               return names;
          }
          
          public List<StudentBillingFinanceExcel>getStudetBills(MultipartFile path, String ClassName) throws IOException{
          XSSFSheet sheet =getCellData(path).getSheet(ClassName);
          List<StudentBillingFinanceExcel>studentBils=new ArrayList<>();
          DataFormatter df=new DataFormatter();
           
          for(Row row:sheet){
            StudentBillingFinanceExcel SBE=new StudentBillingFinanceExcel(); 
            SBE.setNames(df.formatCellValue(row.getCell(1)));
            SBE.setOutstandingBalace(ReturnCellValueFromFomula(row.getCell(16)));
            SBE.setStudentId((df.formatCellValue(row.getCell(0))));
            studentBils.add(SBE);
          }
        return  studentBils;
          }  
           public double ReturnCellValueFromFomula(Cell cell){
    double value=0.0;
        switch(cell.getCellType()){
        case FORMULA:

    value=cell.getNumericCellValue();
    }
        return value;
    }   
}
