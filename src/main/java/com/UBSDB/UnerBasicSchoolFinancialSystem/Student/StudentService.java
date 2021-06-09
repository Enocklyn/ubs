/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.Student;

import com.UBSDB.UnerBasicSchoolFinancialSystem.Bill.BillService;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Bill.StudentBill;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Term.Term;
import com.UBSDB.UnerBasicSchoolFinancialSystem.klass.klass;
import com.UBSDB.UnerBasicSchoolFinancialSystem.klass.klassService;
import com.UBSDB.UnerBasicSchoolFinancialSystem.security.UserDetailsServiceImpl;
import com.UBSDB.UnerBasicSchoolFinancialSystem.security.UserService;
import com.UBSDB.UnerBasicSchoolFinancialSystem.security.role;
import com.UBSDB.UnerBasicSchoolFinancialSystem.security.users;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
     @Autowired
     private UserService US;
     
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
    
    //Student who have stooped school
    public String FlagStudent(String studId){
    
        Student stud=findStudent(studId);
   
         stud.setStudentStatus("Stopped");
         SR.save(stud);
    return "updated Successfully";
       
        }
   
    public String UpdateStudentRecords(Student stud){
    stud=ChangeToUpper(stud);
        
        try{
        Student student=findStudent(stud.getStudentId());
      
    List<StudentBill>bill=student.getBill();
    stud.setBill(bill);
   saveStudent(stud);
    return "updated Successfully";
       }catch(Exception ez){
       return "error"+ez.toString();
       }
    
    }
    
    public String UpdateStudent(Student stud,MultipartFile file){
        
        stud=ChangeToUpper(stud);
        
        try{
        Student student=findStudent(stud.getStudentId());
      
     List<StudentBill>bill=student.getBill();
     stud.setBill(bill);
     SR.save(stud);
    
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
     public String saveStudentWithPicture(Student stud,MultipartFile file){
       stud= ChangeToUpper(stud);
       stud.setStudentStatus("in School");
          try{
           String fileName=org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());
          stud.setStudentPicture(fileName);
   stud.setProductPicturePath("/student-pics/"+stud.getStudentId()+"/"+stud.getStudentPicture());
    
        
    SR.save(stud);
    CreateStudentAccount(stud);
    uploadPicture(file,stud,fileName);
    return "Student "+stud.getFirstName()+" "+stud.getSurname()+" have been added successfully";
    }catch (Exception ex){
    
    return " fatal error please contact admin "+ex.toString();
    }
    
    }
   
    
    public String uploadPicture(MultipartFile multiPartFile,Student stud,String fileName){
    try{
     String uploadDir="stud-pics/"+stud.getStudentklass().getClassName()+"/"+stud.getStudentId();
        Path
                uploadPath=Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
        Files.createDirectories(uploadPath);
        }
        InputStream inputStream=multiPartFile.getInputStream();
        
        Path filePath=uploadPath.resolve(fileName);
        Files.copy( inputStream, filePath,StandardCopyOption.REPLACE_EXISTING);
        return "successfully uploaded";
    }catch(IOException ex){
    return "error"+ex.toString();
    }
    
    }
    
    
    
    public users CreateStudentAccount(Student student){
        users user=new users();
             BCryptPasswordEncoder encode=new BCryptPasswordEncoder();
        
        try{
            
            user.setEnabled(true);
            user.setUsername(student.getStudentId());
      
           user.setPassword(encode.encode(student.getStudentId()+"ubs"));
            role role=new role();
            role.setName("Student");
            Set<role>roles=new HashSet<>();
            roles.add(role);
           user.setRoles(roles);
            US.addUser(user);
        }catch(Exception ex){ex.printStackTrace();
        }
    return user;
    }
    public String ChangeDefaulPassword(String studentId,String Newpassword){
       BCryptPasswordEncoder encode=new BCryptPasswordEncoder();
        
        try{
    users user=US.FindUserById(studentId);
    user.setPassword(encode.encode(Newpassword));
    US.addUser(user);
    return "successfully changed password";
    
    }catch(Exception ex){return " error... "+ex.toString();}
    }
    
    
    public String saveStudent(Student stud){
       stud= ChangeToUpper(stud);
       stud.setStudentStatus("in School");
    try{
        
    SR.save(stud);
    CreateStudentAccount(stud);
    
    return "Student "+stud.getFirstName()+" "+stud.getSurname()+" have been added successfully";
    }catch (Exception ex){
    
    return " fatal error please contact admin "+ex.toString();
    }
    
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
      try{
        List <Student>stud=new ArrayList<>();
    for(Student student:SR.findAll()){
    if(     (student.getStudentklass().getClassName().equals(klass.getClassName()))&(!student.getStudentStatus().equals("Stopped")))
    {
          stud.add(student);
    } else {
    }}
    return stud;
    }catch(Exception ex){
    System.out.println(ex.toString());
    return null;
    
    }
      }
    
    //saving many students into db. Mostly this deal with data from excel
     public String saveStudentsIntoDb(List<Student>students){
     try{
         students.forEach(stud -> this.saveStudent(stud));
               return "saved successfully"+students.toString();
     
     }catch(Exception ex){
     return "fatal error "+ ex.toString()+students.toString();
     
     }
     }


}
