/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSFS.UnerBasicSchoolFinancialSystem.Excel;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.http.HttpResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author enock
 */
@Controller
public class StudentBillingFinanceExcelController {
    @Autowired
    StudentBillingFinanceExcelService SBFES;
    @GetMapping("/SelectExcelFileToUpload")
    public String SelectExcelFileToUpload(Model model){
model.addAttribute("SelectExcelFileToUpload",true);
return "index";
 }
    
    @GetMapping("/ShowExcelForm")
    public String ShowExcelForm(Model model){
    model.addAttribute("excel",true);
    return "index";
    }
    
    @PostMapping("/GetFile")
    public String uploadExcelFile(Model model ,@RequestParam("file")MultipartFile file,HttpServletRequest request) throws IOException{
    model.addAttribute("StudentClasses",SBFES.ClassNames(file));
    request.getSession().setAttribute("file", file);
    // System.out.println(SBFES.ClassNames(file).toString());
    return "index";
    }
    @GetMapping("ShowStudentsDataExcelForm")
    public String ShowStudentsDataExcelForm(Model model){
     model.addAttribute("StudentExcelData", true);
        return "index";
    }
    @PostMapping("/GetStudentsDataExcelFile")
    public String upLoadStudentList(Model model ,@RequestParam("file")MultipartFile file,HttpResponse response) throws IOException{
    //SBFES.GetStudentsFromExcelToDB(file, ClassName)
    System.out.println(".................................."+file.getInputStream());
    
    String msg=SBFES.saveStudentsFromExcelFile(file);
    model.addAttribute("msg",msg );
    model.addAttribute("StudentExcelData", true);
   
    return "index";
    }
    
    @GetMapping("StudetBillingsExcel/{ClassName}")
    public String StudentBillingsExcel(Model model, @PathVariable("ClassName")String ClassName,HttpSession session) throws IOException{
   try{
        MultipartFile file=(MultipartFile) session.getAttribute("file");
    model.addAttribute("StudentClasses",SBFES.ClassNames(file));
   
        model.addAttribute("StudentBills",SBFES.getStudetBills(file, ClassName));
   }catch(Exception ex){
    model.addAttribute("msg","Please error in some fields of file please check file or"
    +"session timed out please upload file again"+ex.toString()
    );
   
   }
    return "index";
    }
  
    }
