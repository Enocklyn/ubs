/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.klass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author enock
 */
@Controller
public class klassController {
    @Autowired
    private klassService kS;
 
    @GetMapping("/klass")
 
 public String saveKlass() {
 
     kS.AddClasses().stream().map(k -> {
        // System.out.print(k.getClassName());
            return k;
        }).forEachOrdered(k -> {
            kS.saveKlass(k);
        });return "index";
 }
 @GetMapping("Claases")
 public String ShowClasses(Model model){
 model.addAttribute("klasses",kS.AllKlass());
 return "index";
 }
    
    
}
