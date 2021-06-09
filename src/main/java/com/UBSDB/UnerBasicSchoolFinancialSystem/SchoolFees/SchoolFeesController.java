/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.SchoolFees;

import com.UBSDB.UnerBasicSchoolFinancialSystem.PTA.PTAService;
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
public class SchoolFeesController {
    @Autowired
    private SchoolFeesService SFS;
   @Autowired
   private PTAService ptas;
    
    @GetMapping("/ShowSchoolFeesForm")
   public String ShowSchoolFeesForm(Model model)
   {
     model.addAttribute("Fees",new SchoolFees());
     model.addAttribute("terms",SFS.Feeterms());
    return "index";
      }
   
   @PostMapping("/savefees")
      public String SaveFess(Model model,SchoolFees fess){
      model.addAttribute("msg",SFS.SaveSchoolFees(fess));
      if(ptas.checkIf_PTA_Is_Entered(fess.getTerm())==false){
          return "redirect:/ShowPTAForm";
      }
      return "index";
      }
      
}
