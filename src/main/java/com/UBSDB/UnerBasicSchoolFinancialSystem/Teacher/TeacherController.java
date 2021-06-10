/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.Teacher;

import com.UBSDB.UnerBasicSchoolFinancialSystem.Assessment.Assessment;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Student.StudentService;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Subject.SubjectSeeder;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Subject.SubjectService;
import com.UBSDB.UnerBasicSchoolFinancialSystem.klass.klassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author enock
 */

@Controller
public class TeacherController {
    @Autowired
    private TeacherService1 TS;
    @Autowired
    private SubjectService Ss;
     @Autowired
    private klassService KS;
          @Autowired
private StudentService StudS;
        @Autowired
        private SubjectSeeder SS;
    @RequestMapping("/TeacherForm")
    public String ShowTeacherForm(Model model){
    model.addAttribute("teacher",new Teacher());
    model.addAttribute("TeacherForm",true);
        return "index";
    }
    
    @RequestMapping("/")
    public String populate(Model model ){
    if(Ss.Allsubjects().isEmpty()){
        if(KS.AllKlass().isEmpty()){
    return "redirect:/klass";
    
    }
   model.addAttribute("msg", SS.SeedAllData());
    }else{
    model.addAttribute("msg","welcome    ");
   
    }
   return "index";
    }
    
    @PostMapping("/saveTeacher")
    public String saveTeacher(Model model,Teacher teacher){
        teacher.setTeacherStatus("True");
        model.addAttribute("msg",TS.addTeacher(teacher));
        
    return "index";
    }
    
    
    @GetMapping("TeacherDatils/{TeacherId}")
    public String getTeacherDetails(Model model,@PathVariable("TeacherId")Long TeacherId)
    {
        
    model.addAttribute("TeacherId",TeacherId);
    model.addAttribute("Teacher",TS.findTeacher(TeacherId));
    model.addAttribute("TeacherKlass",TS.Teacher_Assigned_Klasses(TeacherId));
    return "Teacher";
    }
    
    @GetMapping("TeacherSubjects/{TeacherId}/{ClassId}")
    public String getTeacherSubjets(Model model,@PathVariable("TeacherId")Long TeacherId,@PathVariable("ClassId")String ClassId)
    {
    model.addAttribute("Teacher",TS.findTeacher(TeacherId));
   model.addAttribute("TeacherKlass",TS.Teacher_Assigned_Klasses(TeacherId));
     model.addAttribute("TeacherId",TeacherId);
   
    model.addAttribute("subjects",TS.getSubjectsThoughtByATeacherInAClass(TeacherId, ClassId));
    return "Teacher";
    }
    @GetMapping("Teacher/RemoveSubject/{TeacherId}/{SubjectId}")
    
    public String removeSubject(Model model,@PathVariable("TeacherId")Long TeacherId,@PathVariable("SubjectId")Long SubjectId){
    model.addAttribute("msg",TS.removeSubjectFromTeacherSubjects(TeacherId, SubjectId));
    return "Teacher";
    }
    
    @GetMapping("/AssignClassToTeacher/{TeacherId}")
    public String AssignClassToTeacher(Model model,@PathVariable("TeacherId")Long TeacherId){
    model.addAttribute("Assignklasses",KS.AllKlass());
    model.addAttribute("Name",TS.getTeacherFullName(TeacherId));
    model.addAttribute("TeacherId",TeacherId);
    return "Teacher";
    }
    
    @GetMapping("/AssignClassAndSubjectToTeacher/{TeacherId}/{SubjectId}/{ClassId}")
    public String AssignSubjectAndKlassToTeacher(@PathVariable("TeacherId") Long TeacherId,
    @PathVariable("SubjectId")Long SubjectId,@PathVariable("ClassId")String ClassId,Model model)
   {
    model.addAttribute("msg",TS.AssignTeacherToClass(TeacherId, ClassId, SubjectId));
    model.addAttribute("SubjectsAssigned",TS.SubjectThoughtByTeachersInAParticularClass(ClassId));
        
    return "index";
        
    }
    @GetMapping("/subjectsToBeAssigned/{ClassId}/{TeacherId}")
    public String Show_Subjects_Available_To_BeAssigned_To_Techears_Based_On_Class
        (Model model,@PathVariable("ClassId") String ClassId,@PathVariable("TeacherId")Long TeacherId){
            
            
    model.addAttribute("ClassName",ClassId);
    model.addAttribute("Subjects",TS.Subjecs_Not_Assigned_To_A_Teacher_In_A_Particular_Class(KS.FindKlass(ClassId)));
  
          model.addAttribute("Name",TS.getTeacherFullName(TeacherId));
    model.addAttribute("TeacherId",TeacherId);
    return "Teacher";
    }
        
        @GetMapping("/ClassTeachers/{ClassId}")
        public String Class_Teachers(Model model,@PathVariable("ClassId")String ClassId){
        model.addAttribute("TKS",TS.SubjectThoughtByTeachersInAParticularClass(ClassId));
        return "Teacher";
        }
        
         @GetMapping("/ClassTeachers")
        public String Classes(Model model){
        model.addAttribute("klasses",KS.AllKlass());
        return "Teacher";
        }
        
    @GetMapping("/FindAllTeachers")
    public String Allteachers(Model model){
    model.addAttribute("teachers",TS.listOfTeachers());
    return "index";
    }
    @GetMapping("/TeacherPortal/Detail/{TeacherId}")
    public String TeacherPortalDetail(Model model,@PathVariable("TeacherId")Long TeacherId){
    model.addAttribute("TeacherId",TeacherId);
    model.addAttribute("Teacher",TS.findTeacher(TeacherId));
    return "TeacherPortal";
    
    }
    @GetMapping("/TeacherPortal/{TeacherId}")
    public String TeacherPortal(Model model,@PathVariable("TeacherId")Long TeacherId){
    model.addAttribute("TeacherId",TeacherId);
    model.addAttribute("Teacher",TS.findTeacher(TeacherId));
    return "TeacherPortal";
    
    }
     @GetMapping("/TeacherPortal/Classes/{TeacherId}")
    public String TeacherPortalClasses(Model model,@PathVariable("TeacherId")Long TeacherId){
    model.addAttribute("TeacherId",TeacherId);
     model.addAttribute("TeacherKlass",TS.Teacher_Assigned_Klasses(TeacherId));
     return "TeacherPortal";
    
    }
    
    
    @GetMapping("/TeacherPortal/Students/{TeacherId}/{ClassId}")
    public String TeacherPortalStudents(Model model,@PathVariable("TeacherId")Long TeacherId,
    @PathVariable("ClassId")String ClassId){
     model.addAttribute("TeacherId",TeacherId);
    model.addAttribute("TeacherStudents",StudS.GetStudents_In_A_Particular_Class(KS.FindKlass(ClassId)));
    model.addAttribute("ClassId",ClassId);
    
    return "TeacherPortal";
    }
    
    @GetMapping("/TeacherPortal/Subject/{TeacherId}/{ClassId}")
    public String TeacherPortalSubjects(Model model,@PathVariable("TeacherId")Long TeacherId,
    @PathVariable("ClassId")String ClassId){
     model.addAttribute("TeacherId",TeacherId);
     
    model.addAttribute("TeacherSubjects",TS.getSubjectsThoughtByATeacherInAClass(TeacherId, ClassId));
    model.addAttribute("ClassId",ClassId);
    return "TeacherPortal";
    }
    
    
}
