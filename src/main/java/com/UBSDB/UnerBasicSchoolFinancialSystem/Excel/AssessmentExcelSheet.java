/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.Excel;

import com.UBSDB.UnerBasicSchoolFinancialSystem.Student.Student;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

/**
 *
 * @author enock
 */
@Component
public class AssessmentExcelSheet {
   /* OneToOne
private  Student student;
  
private double TestScores;
  
private double ExamScores;
@OneToOne
private Subject subject;
    */
    
    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private final List<Student> listStudent;
     
    public AssessmentExcelSheet(List<Student> listUsers) {
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
         
             
        createCell(row, 1, "First Name", style);       
        createCell(row, 2, "Last Name", style);    
        createCell(row, 3, "Sur Name", style);
         createCell(row, 4, "Test1(15)", style);
         createCell(row, 5, "Test2(15)", style);
         createCell(row, 6, "ProjectWork(15)", style);
         createCell(row, 7, "Exam Score(100)", style);
         createCell(row, 0, "Student Id", style);
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
            createCell(row, columnCount++, 0, style);
            createCell(row, columnCount++, 0, style);
            createCell(row, columnCount++, 0, style);
            createCell(row, columnCount++, 0, style);
            
             
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
