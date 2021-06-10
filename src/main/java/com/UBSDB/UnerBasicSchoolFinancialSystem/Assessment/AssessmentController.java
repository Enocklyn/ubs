/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.Assessment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author enock
 */
@Controller
public class AssessmentController {
   @PostMapping("TeacherPortal/SaveStudentAssessment/")
   public String AddAssessment(){
   
       return "TeacherPortal";
   }
}
