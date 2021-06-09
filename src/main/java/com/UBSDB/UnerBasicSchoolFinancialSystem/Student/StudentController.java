
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.Student;

import com.UBSDB.UnerBasicSchoolFinancialSystem.Academic.AcademicService;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Assessment.AssessmentService;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Excel.AssessmentExcelSheet;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Excel.StudentBillingFinanceExcelService;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Term.TermService;
import com.UBSDB.UnerBasicSchoolFinancialSystem.klass.klass;
import com.UBSDB.UnerBasicSchoolFinancialSystem.klass.klassService;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StudentController {
    @Autowired
    private StudentBillingFinanceExcelService SbFE;
    @Autowired
    private AssessmentExcelSheet SES;
  
    @Autowired
    private StudentService SS;
    @Autowired 
    private klassService KS;
    @Autowired
    private TermService TS;
    @Autowired
    private AcademicService AS;
   
    @Autowired AssessmentService As;
    @GetMapping("/AddStudentForm")
public String showStudentForm(Model model){
  model.addAttribute("StudentClass", KS.AllKlass());
model.addAttribute("newStudent",new Student());
      
 return "index";   
}
@GetMapping("/FlagStudent/{studentId}/{classId}")
public String FlagStudent(@PathVariable("classId") String classId,@PathVariable("studentId")String studentId,Model model){
 SS.FlagStudent(studentId);
return "redirect:/Studentlist/"+classId;
}

@PostMapping("/SaveStudent")
public String saveStudent(Model model,Student student,@RequestParam("file-image")MultipartFile file){
    
    model.addAttribute("msg",SS.saveStudentWithPicture(student,file));
    return "index";
}
@GetMapping("/Studentlist/{classId}")
public String getStudetnListBasedOnClass(Model model, @PathVariable("classId") String classId){
    klass k=new klass(
    classId,"");
    AS.SaveAcademicYear();
    
 model.addAttribute("StudentList", SS.GetStudents_In_A_Particular_Class(k));
 model.addAttribute("classId",classId );
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
public String UpdateStudent(Model model,Student student,MultipartFile file){
model.addAttribute("msg",SS.UpdateStudent(student,file));
return "index";
}


@GetMapping("/StudentBill/{id}")
public String StudentBill(Model model,@PathVariable("id")String id){
model.addAttribute("Studentbills",SS.findStudent(id).getBill());
model.addAttribute("studentname",SS.findStudent(id).getFirstName());
model.addAttribute("totalBillFees",SS.GetStudentTotalFees(SS.findStudent(id)));
return "index";
}
  @GetMapping("DownloadStudentExcelSheet/{ClassId}")
 
    public void exportToExcel(@PathVariable("ClassId")String ClassId,HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename="+ClassId+"_"+ currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<Student> listUsers = SS.GetStudents_In_A_Particular_Class(KS.FindKlass(ClassId));
        SbFE=new  StudentBillingFinanceExcelService(listUsers);
         
        SbFE.export(response,ClassId);    
    } 
    @GetMapping("DownloadStudentAseessmentExcelSheet/{ClassId}")
    public void DownloadStudentAseessmentExcelSheet(@PathVariable("ClassId")String ClassId
            ,HttpServletResponse response) throws IOException{
    response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         //SES
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename="+ClassId+"_"+ currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<Student> listUsers = SS.GetStudents_In_A_Particular_Class(KS.FindKlass(ClassId));
        SES=new  AssessmentExcelSheet(listUsers);
         
        SES.export(response,ClassId); }
    
@GetMapping("StudentPortal/Assessment/")
public String StudentPortalAssessment(Model model,Principal principal){
String StudentId=principal.getName();
model.addAttribute("StudentAssessment",As.getAssessmentBasedOnTerm(TS.getCurrentTerm(), SS.findStudent(StudentId)));
model.addAttribute("StudentDetailPicture",SS.findStudent(StudentId));
return "StudentPortal";
}

@GetMapping("StudentPortal/Detail/")
public String StudentDetail(Model model,Principal principal){
String StudentId=principal.getName();
model.addAttribute("StudentDetails",SS.findStudent(StudentId));
return "StudentPortal";
}

@GetMapping("StudentPortal/")
public String StudentPortal(Model model,Principal principal){
String StudentId=principal.getName();
if(SS.findStudent(StudentId).getStudentStatus().equals("Stopped")){
return "login";
}
model.addAttribute("StudentId",StudentId);
return "StudentPortal";
}
@GetMapping("StudentPortal/getBills")
public String ShowStudentBills(Model model,Principal principal){
String id=principal.getName();
model.addAttribute("Studentbills",SS.findStudent(id).getBill());
model.addAttribute("studentname",SS.findStudent(id).getFirstName());
model.addAttribute("totalBillFees",SS.GetStudentTotalFees(SS.findStudent(id)));

return "StudentPortal";
}
}