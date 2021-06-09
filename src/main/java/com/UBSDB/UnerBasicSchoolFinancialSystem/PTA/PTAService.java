/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSFS.UnerBasicSchoolFinancialSystem.PTA;

import com.UBSFS.UnerBasicSchoolFinancialSystem.Bill.BillService;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Bill.StudentBill;
import com.UBSFS.UnerBasicSchoolFinancialSystem.SchoolFees.SchoolFeesService;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Student.Student;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Student.StudentService;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Term.Term;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author enock
 */
@Service
public class PTAService {
    @Autowired
    private PTARepository ptaR;
   
    @Autowired
    private BillService  BS;
    private StudentService SS;
     @Autowired
     private SchoolFeesService SFS;
    
     public String SavePTA(PTA pta){
        List<StudentBill>bills=new ArrayList<>();
    for(PTA pt:ptaR.findAll()){
    if ((pt.getTerm().getAcademicyear().
            getAcademicYear().getYear()==(pta.getTerm().
                    getAcademicyear().getAcademicYear().getYear()))&&
            (pt.getTerm().getTermName().equals(pta.getTerm().getTermName()))){
    
    return "PTA have been added already for the term";
    }}
    try{
        for(StudentBill bill:BS.bills()){
    if((bill.getTerm().getAcademicyear().getAcademicYear().getYear()==pta.getTerm().
            getAcademicyear().getAcademicYear().getYear())
            &&(bill.getTerm().getTermName().equals(pta.getTerm().getTermName()))){
       ptaR.save(pta);
        
       bill.setPta(pta);
        
       BS.saveBill(bill);
    } //return "saved succesfully";
     } return "savedSuccessfully"+pta.getTerm().getTermName();
      
    }catch(Exception ex){   return "error contact admin"+ex.toString();
    } //return "please add Fess for this term "+pta.getTerm().getTermName();
   
}
   
   
    public Set<Term> PTATerm(){
   return SFS.Feeterms();
   
   }
}
