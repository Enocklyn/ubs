/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.Excel;

import com.UBSDB.UnerBasicSchoolFinancialSystem.Assessment.Assessment;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Assessment.AssessmentService;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Student.Student;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Student.StudentService;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Subject.SubjectService;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Term.Term;
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
import com.UBSDB.UnerBasicSchoolFinancialSystem.klass.klassService;
import java.util.stream.Collectors;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
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
        @Autowired
        private SubjectService Ss;
        @Autowired
        private AssessmentService AS;
        
        
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
     stud.setSex(df.formatCellValue(row.getCell(5)));
     stud.setPhysicalDisability("");
     stud.setOccupation("");
     stud.setDateOfBirth(df.formatCellValue(row.getCell(6)));
     stud.setParentCategory("");
     stud.setResidentialAddress("");
     stud.setSurname(df.formatCellValue(row.getCell(3)));
     //    System.out.println(kS.FindKlass(sheet.getSheetName()).getClassName());
     students.add(stud);
     }
    return  students;
   }
   
   //Saving assessment into db using excel file
   public List<Assessment>GetStudentAssessmentFromExcelSheet(MultipartFile path,Long SubjectId,Term term)throws IOException{
   
   XSSFSheet sheet =getCellData(path).getSheetAt(0);
          List<Assessment>Assesment= new ArrayList<>();  
        DataFormatter df=new DataFormatter(); int a=0;
        
   for(int rowCount=sheet.getFirstRowNum()+1;rowCount<=sheet.getLastRowNum();rowCount++){
      
       Student student=SS.findStudent(sheet.getRow(rowCount).getCell(0).getStringCellValue());
   Assessment assess=new Assessment();
   assess.setStudent(student);
   assess.setTerm(term);
   assess.setSubject(Ss.findAParticularSubject(SubjectId));
   double FirstTestScore=sheet.getRow(rowCount).getCell(4).getNumericCellValue();
    double SecondTestScore=sheet.getRow(rowCount).getCell(5).getNumericCellValue();
    double ThirdTestScore=sheet.getRow(rowCount).getCell(6).getNumericCellValue();
  assess.setTotalTestScore(FirstTestScore+SecondTestScore+ThirdTestScore);
   assess.setTestScoreAverage((assess.getTotalTestScore()/60)*50);
   assess.setExamScores(sheet.getRow(rowCount).getCell(7).getNumericCellValue());
   assess.setExamsAverage((50*assess.getExamScores())/100);
   assess.setTotalTestScoreAverage((assess.getTotalTestScore()*50)/100);
   assess.setTotalScore(assess.getExamsAverage()+assess.getTotalTestScoreAverage());
   Assesment.add(assess);
   a++;
   }return Assesment;}
   
   
   
   public String saveStudentsFromExcelFile(MultipartFile path) throws IOException{
   return SS.saveStudentsIntoDb(GetStudentsFromExcelToDB( path));
   }
   
   
    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private final List<Student> listStudent;
     
    public StudentBillingFinanceExcelService(List<Student> listUsers) {
        this.listStudent = listUsers;
        
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine(String ClassName) {
        sheet = workbook.createSheet(ClassName);
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "ID", style);      
        createCell(row, 1, "First Name", style);       
        createCell(row, 2, "Last Name", style);    
        createCell(row, 3, "Sur Name", style);
         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (Student user : listStudent) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, user.getStudentId(), style);
            createCell(row, columnCount++, user.getFirstName(), style);
            createCell(row, columnCount++, user.getLastName(), style);
            createCell(row, columnCount++, user.getSurname(), style);
            
             
        }
    }
     
    public void export(HttpServletResponse response,String ClassName) throws IOException {
        writeHeaderLine(ClassName);
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }

    
    
}
   