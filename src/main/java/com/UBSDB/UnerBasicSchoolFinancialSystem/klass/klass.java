/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.klass;

import com.UBSDB.UnerBasicSchoolFinancialSystem.Subject.Subject;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author enock
 */
@Entity
public class klass {
  
  @Id
  private String ClassName;

    public klass() {
    }
    
   
    public klass(String ClassName, String ClassLevel) {
        this.ClassName = ClassName;
        this.ClassLevel = ClassLevel;
    }
 
  private String ClassLevel;
  
  public String getClassName() {
        return ClassName;
    }

    public void setClassName(String ClassName) {
        this.ClassName = ClassName;
    }

    public String getClassLevel() {
        return ClassLevel;
    }

    public void setClassLevel(String ClassLevel) {
        this.ClassLevel = ClassLevel;
    }

   
    
    
    
}
