/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSFS.UnerBasicSchoolFinancialSystem.Term;

import com.UBSFS.UnerBasicSchoolFinancialSystem.Academic.Academic;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Academic.AcademicService;
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
public class TermService {
   @Autowired
   private TermRepository TS;
   @Autowired 
   private AcademicService AS;
    public String SaveTerm(Term term){
      if(LocalDate.parse(term.getStartDate()).getYear()!=LocalDate.now().getYear()||
              LocalDate.parse(term.getEndDate()).getYear()!=LocalDate.now().getYear()
              
              ){ return "please the year input is incorrect please correct it to "+LocalDate.now().getYear();
      } for (Term t:terms()){
    if((t.getAcademicyear().equals(term.getAcademicyear()))&&(t.getTermName().contains(term.getTermName()))){
      return "Term has been added already";
        } }  TS.save(term);
   return "saved Term successfully";
    }
    
      
    
    
    
    public List<Term>terms(){
     return TS.findAll();
    }
    
    public List<Term>BasedOnAcademicYear(Academic academic){
    List <Term>t=new ArrayList<>();
        for(Term term:TS.findAll()){
    if(term.getAcademicyear().getAcademicYear().equals(academic.getAcademicYear())){
    
    t.add(term);
    
    }
    
    }
    return t;
    }
    
    public Term checkIfAcademicYearIsSet(){
        Term term=new Term();
    LocalDate d= LocalDate.now();
    if(AS.AllAcademic().isEmpty()){
      
     term.setAcademicyear(AS.SaveAcademicYear());
    }else{
    for(Academic Aca: AS.AllAcademic()){
    //checking if academic Year have been set for that term
    
     if(Aca.getAcademicYear().getYear()!=d.getYear()){
      AS.SaveAcademicYear();  
      term.setAcademicyear(Aca);
       
     }else{
         
         term.setAcademicyear(Aca);
         
     }} }
   
     return term;
    
    }
    public Term CheckIfTermExistForTheAcademicYear(){
        Term t=checkIfAcademicYearIsSet();
     
        return t;
    }
}
