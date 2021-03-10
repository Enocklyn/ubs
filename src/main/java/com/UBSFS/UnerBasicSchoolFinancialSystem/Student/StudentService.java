/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSFS.UnerBasicSchoolFinancialSystem.Student;

import com.UBSFS.UnerBasicSchoolFinancialSystem.Bill.BillService;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Bill.StudentBill;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Term.Term;
import com.UBSFS.UnerBasicSchoolFinancialSystem.klass.klass;
import com.UBSFS.UnerBasicSchoolFinancialSystem.klass.klassService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author enock
 */
@Service
public class StudentService {
   @Autowired
     private StudentRepository SR;
     @Autowired
     private BillService BS; 
     @Autowired
     private klassService KS;
     
    public String promoteStudent(Student student){
    klass klas=null;
  
        klas=student.getStudentklass();
        
        if(klas.getClassName().equals("LowerCreche")){
          
          student.setStudentklass(KS.FindKlass("UpperCreche"));
          try{
      return SR.save(student)+"successfuly";
          }catch(Exception ex){
          return "fatal error;"+ex.toString();
          }
        }
         if(klas.getClassName().equals("UpperCreche")){
      
          student.setStudentklass(KS.FindKlass("Nursery1"));
         try{
      return SR.save(student)+"successfuly";
          }catch(Exception ex){
          return "fatal error;"+ex.toString();
          }
         }
         
        if(klas.getClassName().equals("Nursery1")){
     
          student.setStudentklass(KS.FindKlass("Nursery2"));
         try{
      return SR.save(student)+"successfuly";
          }catch(Exception ex){
          return "fatal error;"+ex.toString();
          }
        }
        
        if(klas.getClassName().equals("Nursery2")){
      
          student.setStudentklass(KS.FindKlass("KG1"));
         try{
      return SR.save(student)+"successfuly";
          }catch(Exception ex){
          return "fatal error;"+ex.toString();
          }
        }
        
        if(klas.getClassName().equals("KG1")){
     
          student.setStudentklass(KS.FindKlass("KG2"));
         try{
      return SR.save(student)+"successfuly";
          }catch(Exception ex){
          return "fatal error;"+ex.toString();
          }
        }
         if(klas.getClassName().equals("Basic5")){
      
          student.setStudentklass(KS.FindKlass("Basic6"));
         try{
      return SR.save(student)+"successfuly";
          }catch(Exception ex){
          return "fatal error;"+ex.toString();
          }
         }
          if(klas.getClassName().equals("KG2")){
      
          student.setStudentklass(KS.FindKlass("Basic1"));
           try{
      return SR.save(student)+"successfuly";
          }catch(Exception ex){
          return "fatal error;"+ex.toString();
          }
          
          }
        
       if(klas.getClassName().equals("Basic1")){
      
          student.setStudentklass(KS.FindKlass("Basic2"));
         try{
      return SR.save(student)+"successfuly";
          }catch(Exception ex){
          return "fatal error;"+ex.toString();
          }
       }
         if(klas.getClassName().equals("Basic2")){
     
          student.setStudentklass(KS.FindKlass("Basic3"));
         try{
      return SR.save(student)+"successfuly";
          }catch(Exception ex){
          return "fatal error;"+ex.toString();
          }
         }
     if(klas.getClassName().equals("Basic3")){
    
          student.setStudentklass(KS.FindKlass("Basic4"));
        try{
      return SR.save(student)+"successfuly";
          }catch(Exception ex){
          return "fatal error;"+ex.toString();
          } 
     }
     
       if(klas.getClassName().equals("Basic4")){
      
          student.setStudentklass(KS.FindKlass("Basic5"));
        try{
      return SR.save(student)+"successfuly";
          }catch(Exception ex){
          return "fatal error;"+ex.toString();
          }
       }
        if(klas.getClassName().equals("Basic6")){
     
          student.setStudentklass(KS.FindKlass("JHS1"));
         try{
      return SR.save(student)+"successfuly";
          }catch(Exception ex){
          return "fatal error;"+ex.toString();
          }
        }
        if(klas.getClassName().equals("JHS1")){
      
          student.setStudentklass(KS.FindKlass("JHS2"));
        try{
      return SR.save(student)+"successfuly";
          }catch(Exception ex){
          return "fatal error;"+ex.toString();
          }
      }
        if(klas.getClassName().equals("JHS2")){
     
          student.setStudentklass(KS.FindKlass("JHS3"));
       try{
      return SR.save(student)+"successfuly";
          }catch(Exception ex){
          return "fatal error;"+ex.toString();
          }
      }
        return "null";
        }
    
    public String UpdateStudent(Student stud){
      stud=ChangeToUpper(stud);
        try{
        Student student=findStudent(stud.getStudentId());
        
        List<StudentBill>bill=new ArrayList<>();
    bill=student.getBill();
    stud.setBill(bill);
    saveStudent(stud);
    return "updated Successfully";
       }catch(Exception ez){
       return "error"+ez.toString();
       }
    }
    
    public Student ChangeToUpper(Student stud){
    stud.setFirstName(stud.getFirstName().toUpperCase());
        stud.setLastName(stud.getLastName().toUpperCase());
        stud.setGuadiaName(stud.getGuadiaName().toUpperCase());
        stud.setOccupation(stud.getOccupation().toUpperCase());
        stud.setSurname(stud.getSurname().toUpperCase());
        
        return stud;
    
    }
    
    public String saveStudent(Student stud){
       stud= ChangeToUpper(stud);
    try{
        
    SR.save(stud);
    return "Student "+stud.getFirstName()+" "+stud.getSurname()+" have been added successfully";
    }catch (Exception ex){
    
    return " fatal error please contact admin "+ex.toString();
    }
    
    }
    public String editStudetDetails(Student stud){
    if(SR.findById(stud.getStudentId()).isPresent()){
    
    return saveStudent(stud);
    }
    return "null";
    }
    public Student findStudent(String id){
    return SR.getOne(id);
    }
    
    public double GetStudentTotalFees(Student student){
    double getTotalStudentBills=0;
    for(StudentBill bill :student.getBill() ){
    getTotalStudentBills = getTotalStudentBills+ bill.getFee().getAmount();

    }    return  getTotalStudentBills;
    }
    
    
    public StudentBill GetStudentBillForATerm(Student stud,Term term){
    
        for (StudentBill studbill:BS.bills()){
        if(studbill.getTerm().getId().equals(term)){
          if (stud.getParentCategory().equals(studbill.getFee().getFeeSpecification()))
              
        return studbill;
        } }
    return null;
    }
    
    public List<Student>students(){
    
    return SR.findAll();
    }
    public List<Student>GetStudents_In_A_Particular_Class(klass klass){
        List <Student>stud=new ArrayList<>();
    for(Student student:SR.findAll()){
    if(student.getStudentklass().getClassName().equals(klass.getClassName()))
    {
    stud.add(student); }}
    return stud;
    }
}
