/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSFS.UnerBasicSchoolFinancialSystem.Academic;

import com.UBSFS.UnerBasicSchoolFinancialSystem.Student.Student;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Student.StudentService;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Term.Term;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Term.TermService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

     /**
     *
     * @author enock
     */
      @Service
      public class AcademicService {
      
    
      @Autowired 
      private AcademicRepository AR;
      @Autowired
      private StudentService SS; 
      public Academic  SaveAcademicYear(){
         Academic aca=new Academic();
      aca.setAcademicYear(LocalDate.now());
      String ms="";
         
      if(AllAcademic().isEmpty()){  
      try{return AR.save(aca);}catch(Exception ex){
        } }else{if(CompareDate(aca.getAcademicYear().getYear())==false){ 
            
            for(Student stud:SS.students()){
            System.out.println(SS.promoteStudent(stud));
            }
            
            return AR.save(aca);
       }
      }
      return aca;
      }
      
      
      
    public boolean CompareDate(int date){
    List <Integer> ddate=new ArrayList<>();
    for(Academic newAca:AllAcademic())
    {
    ddate.add(newAca.getAcademicYear().getYear());
    
    }
    if(ddate.isEmpty()){}else{
    if(ddate.contains(date)){return true;  }else {
    return false;
    } }
  return false;}
      
    
     public List<Academic>AllAcademic(){
   
     return AR.findAll();
     } 
    
}
