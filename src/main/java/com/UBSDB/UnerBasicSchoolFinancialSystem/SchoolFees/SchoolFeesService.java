/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.SchoolFees;

import com.UBSDB.UnerBasicSchoolFinancialSystem.Bill.BillService;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Bill.StudentBill;
import com.UBSDB.UnerBasicSchoolFinancialSystem.PTA.PTAService;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Student.Student;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Student.StudentService;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Term.Term;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Term.TermService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author enock
 */

@org.springframework.stereotype.Service
public class SchoolFeesService {
    @Autowired
    private StudentService SS;
    
     @Autowired
     private SchoolFeesRepository SR;
      @Autowired
      private TermService TS;
     
    @Autowired
    private BillService BS;
    
      @Autowired
      private PTAService PTAS;
    
    
    
    public String SaveSchoolFees(SchoolFees fee){
        fee.setFeesDate(LocalDate.now());
        
    if(CheckIfStaffOrNonStaffFeesHaveBeenAssigned( fee).equals("true")){
        try{
        
       StudentBill bill=new StudentBill();SR.save(fee); 
       bill.setFee(fee);bill.setTerm(fee.getTerm());
        BS.saveBill(bill); AssignBillToStudentBasedOnFees(bill);
       return "saved successfully ";}catch (Exception ex){return "error" +ex.toString();}
    }return CheckIfStaffOrNonStaffFeesHaveBeenAssigned( fee);
    }
    
    
    public String CheckIfStaffOrNonStaffFeesHaveBeenAssigned(SchoolFees fee){
    
        for(StudentBill bill:BS.bills()){
     if((bill.getFee().getFeesDate().getYear()==fee.getFeesDate().getYear())&&
             (bill.getTerm().getTermName().equals(fee.getTerm().getTermName()))
             &&(bill.getFee().getFeeSpecification().equals(fee.getFeeSpecification())))
             
             {
     //check if either staff or non staff fee is assigned
      return "please fees for"+fee.getFeeSpecification()+" have been assigned ";
    
    }}
          return "true";
    }
    
    
    
    public void AssignBillToStudentBasedOnFees(StudentBill bill){
        
         for(Student stud:SS.students()){
         if(stud.getParentCategory().equals(bill.getFee()
                 .getFeeSpecification())&(stud.getStudentStatus()
                         .equals("in School"))){
      List<StudentBill>bil= stud.getBill();
      bil.add(bill);
      stud.setStudentId(stud.getStudentId());
      
        stud.setBill(bil);
        SS.saveStudent(stud);
         
         }
         
         }
         
         }
          
            
    
    
    
    
    public Set<Term>Feeterms(){
        Set<Term>terms=new HashSet<>();
        for(Term t:TS.terms()){
        if(t.getAcademicyear().getAcademicYear().getYear()==LocalDate.now().getYear()){
            t.setTermName(t.getTermName()+"-"+t.getAcademicyear().getAcademicYear().getYear());
        terms.add(t);
        
        }
        }
    return terms;
    
    }
}
