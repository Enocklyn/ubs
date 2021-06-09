/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.Term;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author enock
 */

@Controller
public class TermController {
 
    @Autowired
 private TermService TS;
 
    @GetMapping("/term")
    public String addTerm(Model model){
        
      model.addAttribute("newterm",TS.CheckIfTermExistForTheAcademicYear());
      model.addAttribute("year",TS.CheckIfTermExistForTheAcademicYear().getAcademicyear().getAcademicYear().getYear());
    return "index";
    }
    
    
    @PostMapping("/saveTerm")
    public String saveTerm(Model model,Term term){
     if(term.getStartDate().equals("")||term.getEndDate().equals("")){
      model.addAttribute("newterm",TS.CheckIfTermExistForTheAcademicYear());
      model.addAttribute("year",TS.CheckIfTermExistForTheAcademicYear().getAcademicyear().getAcademicYear().getYear());
      model.addAttribute("msg","please fill the form correctly");
   
      return "index";
     }
    model.addAttribute("msg",TS.SaveTerm(term));
    return "index";
    }
    
    
}
