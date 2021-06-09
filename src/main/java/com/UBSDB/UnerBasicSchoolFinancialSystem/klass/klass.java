/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSFS.UnerBasicSchoolFinancialSystem.klass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
