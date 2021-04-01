/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSFS.UnerBasicSchoolFinancialSystem.Excel;

import com.UBSFS.UnerBasicSchoolFinancialSystem.Student.Student;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Student.StudentService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.UBSFS.UnerBasicSchoolFinancialSystem.klass.klassService;
/**
 *
 * @author enock
 */
@Service
public class StudentBillingFinanceExcelService {
 //public   
        @Autowired
        private klassService kS;
        @Autowired
        private StudentService SS;
          public   XSSFWorkbook getCellData( MultipartFile file) throws IOException{
           
            XSSFWorkbook  workbook= new XSSFWorkbook((file.getInputStream()));
          return workbook;
          }
          
          public List<String>ClassNames( MultipartFile getPath) throws IOException{
          List<String>names=new ArrayList<>();
            for(int a=0; a<=getCellData( getPath).getNumberOfSheets()-1;a++){
             names.add(getCellData( getPath).getSheetName(a));
            }  
               return names;
          }
         public String ClassName(MultipartFile getPath) throws IOException{
             System.out.println(getCellData(getPath).getSheetName(0));
         return  getCellData(getPath).getSheetName(0);
         
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
   
           public List<Student> GetStudentsFromExcelToDB(MultipartFile path) throws IOException{
          XSSFSheet sheet =getCellData(path).getSheetAt(0);
          List<Student>students= new ArrayList<>();  
        DataFormatter df=new DataFormatter();
         
     for(Row row:sheet){
     Student stud= new Student();
     stud.setFirstName(df.formatCellValue(row.getCell(1)));
     stud.setLastName(df.formatCellValue(row.getCell(2)));
     stud.setParentNumber(df.formatCellValue(row.getCell(4)));
     stud.setStudentId(df.formatCellValue(row.getCell(0)));
     stud.setStudentklass(kS.FindKlass(sheet.getSheetName()));
     stud.setGuadiaName("");
     stud.setNHISCardumber("");
     stud.setStaffId("");
     stud.setPhysicalDisability("");
     stud.setOccupation("");
     stud.setDateOfBirth("");
     stud.setParentCategory("");
     stud.setResidentialAddress("");
     stud.setSurname(df.formatCellValue(row.getCell(3)));
     //    System.out.println(kS.FindKlass(sheet.getSheetName()).getClassName());
     students.add(stud);
     }
    return  students;
   }
   
   public String saveStudentsFromExcelFile(MultipartFile path) throws IOException{
   
   
   return SS.saveStudentsIntoDb(GetStudentsFromExcelToDB( path));
   }
            
}
