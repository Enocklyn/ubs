/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSFS.UnerBasicSchoolFinancialSystem.klass;

import com.UBSFS.UnerBasicSchoolFinancialSystem.Student.Student;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class klassService {

   @Autowired
   private klassRepository ks;
   
   public String saveKlass(klass klss){
   try{
   ks.save(klss);
   return "successfully saved Class";
   }catch (Exception ex){return " fata error "+ex.toString(); }
   }
   
   public Set<klass>AddClasses(){
   Set<klass>klasses=new HashSet<>();
   klass klas=new klass(
   "Basic1","LowerPrimary"
   );
    klasses.add(klas);
   klass klas2=new klass(
   "LowerCreche","LowerCreche"
   );
   klasses.add(klas2);
    
   klass klas3=new klass(
   "UpperCreche"," UpperCreche"
   );
   klasses.add(klas3);
    klass klas4=new klass(
   "Nursery1","Nursery1"
   );
     klasses.add(klas4);
     klass klas5=new klass(
   "Nursery2","Nursery2"
   ); klasses.add(klas5);
     
  for(int a=1;a<=6;a++){
  klass klassr=new klass();
  klassr.setClassName("Basic"+a);
  if(a<=3){
  klassr.setClassLevel("LowerPrimary");
  }else{
  klassr.setClassLevel("UpperPrimary");
  }
  
  klasses.add(klassr);
  }
  for(int a=1;a<=4;a++){
   klass klassr=new klass();
   klassr.setClassName("JHS"+a);
    klassr.setClassLevel("JuniorHigh");
 
 klasses.add(klassr);
  }
 
  
  return klasses;
   }
   public klass FindKlass(String id){
   return ks.getOne(id);
   }
   
   public List<klass>AllKlass(){
   return ks.findAll();
   
   }
}
