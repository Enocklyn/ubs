/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.Bill;

import com.UBSDB.UnerBasicSchoolFinancialSystem.Student.Student;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Student.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author enock
 */
@Service
public class BillService {
   @Autowired
   private BillRepository BR;
 
    public String saveBill(StudentBill bill){
    
    try{
       BR.save(bill);
    
    return "saved successfully";
    }catch(Exception ex){
    return "error pls contact admin"+ex.toString();
    }}
    
    public List<StudentBill>bills(){
    
    return BR.findAll();
    }
    
  
    
    
 }

