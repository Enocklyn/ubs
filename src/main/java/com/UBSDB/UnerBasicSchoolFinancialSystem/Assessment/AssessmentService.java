/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.Assessment;

import com.UBSDB.UnerBasicSchoolFinancialSystem.Academic.Academic;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Student.Student;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Term.Term;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author enock
 */
@Service
public class AssessmentService {
   @Autowired
   private AssessmentRepo AR;
   
   public String saveAssessment(Assessment assessment){
       try{
       AR.save(assessment);
       
       return "Successfully save";
       
       }catch(Exception ex){
       
       return "Error "+ ex.toString();
       }
   }
   public List<Assessment>FindAll(){
   
   return AR.findAll();
   }
   
  public Map<Term,List<Assessment>> getAssessmentBasedOnTerm(Term term,Student student){
  List<Assessment>assess=new ArrayList<>();
  Map<Term,List<Assessment>>TempTermAssessment=new HashMap<>();
      for(Assessment TempAsessment:FindAll()){
  if((TempAsessment.getTerm().getId().equals(term.getId())&&(TempAsessment.getStudent().getStudentId().equals(student.getStudentId())))){
  
  assess.add(TempAsessment);
  }
  
  }
      TempTermAssessment.put(term, assess);
  return TempTermAssessment;
  }
  
}
