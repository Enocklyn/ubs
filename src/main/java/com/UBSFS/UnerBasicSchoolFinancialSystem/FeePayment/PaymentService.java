/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSFS.UnerBasicSchoolFinancialSystem.FeePayment;

import com.UBSFS.UnerBasicSchoolFinancialSystem.Academic.Academic;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Bill.BillService;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Bill.StudentBill;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Student.Student;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Student.StudentService;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Term.Term;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Term.TermService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author enock
 */
@Service
public class PaymentService {
    @Autowired
    private PayementRepository PS;
   
    @Autowired
    private StudentService SS;
    @Autowired TermService TS;
   public double getTotalFeessPaymentForStudent(Payments payment){
   double totalSchoolFeesPayment=0;
    for (Payments p:AllPayments()){
   if(p.getStudent().getStudentId().equals(payment.getStudent().getStudentId())){
        if(p.getPaymentType().equalsIgnoreCase("SchoolFees")){
    totalSchoolFeesPayment=totalSchoolFeesPayment+p.getAmount();
     }
   
   }}
   return totalSchoolFeesPayment;
    }
   
  public List<Payments>payments(String studId){
  List<Payments>payment=new ArrayList<>();
  for(Payments pay:AllPayments()){
  if(pay.getStudent().getStudentId().equals(studId)){
  
  payment.add(pay);
  }
  
  }
  return payment;
  } 
  
  public Payments ShowNewPayment(String studentId,String paymentType){
     if(GetPaymentTerm()!=null){
        Student student=SS.findStudent(studentId);
    Payments payment = new Payments();
    payment.setStudent(student);
    payment.setTerm(GetPaymentTerm());
    payment.setPaymentType(paymentType);
    return payment;
    }
  return null;
  }
  
  public List<Payments> GetPaymentForAcademicYear(Academic Year){
  List<Payments>payment=new ArrayList<>();
      for(Payments pay:AllPayments()){
      if(pay.getTerm().getAcademicyear().getAcademicYear().getYear()==Year.getAcademicYear().getYear())
      payment.add(pay);
      }
  
  return payment;
  }
    
   //am sorry didnt know is in the controller till i finished
    public Term GetPaymentTerm(){
    for(Term t:TS.terms()){
       if(LocalDate.now().isBefore(LocalDate.parse(t.getStartDate()))||LocalDate.now().
               isAfter(LocalDate.parse(t.getEndDate()))==true){
       } else {
           return t;
        }
    
    }
    return null;
    }
  
  
   public double GetBillForParticularStudent(Payments payment){
       double TotalBills=0;
     for(StudentBill bil :SS.findStudent(payment.getStudent().getStudentId()).getBill()){
      TotalBills=TotalBills+bil.getFee().getAmount();
    }
     return  TotalBills;
   }
   
   public  String SaveSchoolFeesPayment(Payments payment){
    payment.setRemaining(GetBillForParticularStudent(payment)-getTotalFeessPaymentForStudent(payment));
   try{
       PS.save(payment);
     return "saved successfully" ;
    }catch(Exception ex){return"error";
   } }
   public List<Payments>AllPayments(){
    
    return PS.findAll();
    }
    
    
    
}
