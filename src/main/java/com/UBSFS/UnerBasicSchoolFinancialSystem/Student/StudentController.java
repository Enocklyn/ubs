/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSFS.UnerBasicSchoolFinancialSystem.Student;

import com.UBSFS.UnerBasicSchoolFinancialSystem.Academic.AcademicService;
import com.UBSFS.UnerBasicSchoolFinancialSystem.klass.klass;
import com.UBSFS.UnerBasicSchoolFinancialSystem.klass.klassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author enock
 */

@Controller
public class StudentController {
    @Autowired
    private StudentService SS;
    @Autowired 
    private klassService KS;
    
    @Autowired
    private AcademicService AS;
    
    @GetMapping("/AddStudentForm")
public String showStudentForm(Model model){
  model.addAttribute("StudentClass", KS.AllKlass());
model.addAttribute("newStudent",new Student());
      
 return "index";   
}
@PostMapping("/SaveStudent")
public String saveStudent(Model model,Student student){
    
    model.addAttribute("msg",SS.saveStudent(student));
    return "index";
}
@GetMapping("/Studentlist/{classId}")
public String getStudetnListBasedOnClass(Model model, @PathVariable("classId") String classId){
    klass k=new klass(
    classId,"");
    AS.SaveAcademicYear();
    
 model.addAttribute("StudentList", SS.GetStudents_In_A_Particular_Class(k));
  return "index";  
}

@GetMapping("/StudentDetails/{id}")
public String GetStudentDetails(Model model,@PathVariable("id")String id){
model.addAttribute("StudentDetails",SS.findStudent(id));
return "index";
}
@GetMapping("/UpdateDetails/{id}")
public String UpdateStudentDetails(Model model,@PathVariable("id")String id){
model.addAttribute("UpdateStudent",SS.findStudent(id));
return "index";
}
@PostMapping("/update")
public String UpdateStudent(Model model,Student student){
model.addAttribute("msg",SS.UpdateStudent(student));
return "index";
}
@GetMapping("/StudentBill/{id}")
public String StudentBill(Model model,@PathVariable("id")String id){
model.addAttribute("Studentbills",SS.findStudent(id).getBill());
model.addAttribute("studentname",SS.findStudent(id).getFirstName());
model.addAttribute("totalBillFees",SS.GetStudentTotalFees(SS.findStudent(id)));
return "index";
}
}