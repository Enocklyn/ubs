/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSFS.UnerBasicSchoolFinancialSystem.FeePayment;

import com.UBSFS.UnerBasicSchoolFinancialSystem.Student.Student;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Student.StudentService;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Term.Term;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Term.TermService;
import java.time.LocalDate;
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
public class PaymentController {
    @Autowired
    private StudentService SS;
    @Autowired
    private PaymentService PS;
    
    @GetMapping("/PaymentForm/{id}/{paymentType}")
    public String ShowPaymentForm(Model model,@PathVariable("id")String id,
            @PathVariable("paymentType")String paymentType){
     model.addAttribute("payment",PS.ShowNewPayment(id,paymentType));
     model.addAttribute("info","please enter "+paymentType+" for  "+SS.findStudent(id).getFirstName()+" "+
             SS.findStudent(id).getLastName()+" "+ SS.findStudent(id).getSurname());
     model.addAttribute("StudentList",SS.GetStudents_In_A_Particular_Class(SS.findStudent(id).getStudentklass()));
     return "index";
      }
    
    @PostMapping("/SavePayment")
    public String savePayment(Model model,Payments payment){
    model.addAttribute("msg",PS.SaveSchoolFeesPayment(payment));
    model.addAttribute("StudentList",SS.GetStudents_In_A_Particular_Class(SS.findStudent(payment.getStudent().getStudentId()).getStudentklass()));
    
    return "index";
    }
    
    @GetMapping("/GetStudentPayments/{id}")
    public String GetStudentPayments(Model model ,@PathVariable("id")String id){
       model.addAttribute("studentPayments",PS.payments(id));
        return "index";
    }
    
   
}
