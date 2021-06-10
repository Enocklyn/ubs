/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.Subject;

import com.UBSDB.UnerBasicSchoolFinancialSystem.klass.klass;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author enock
 */
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;  

     @ManyToMany 
    @JoinTable(
    name = "Class_Subjects",
    joinColumns = @JoinColumn(name = "id"),
    inverseJoinColumns = @JoinColumn(name = "ClassName") ) 
    private List<klass>klaases;

   

    
     
     
    public List<klass> getKlaases() {
        return klaases;
    }

    public void setKlaases(List<klass> klaases) {
        this.klaases = klaases;
    }
   
    public Long getId() {
        return id;
    }
private String SubjectName;
    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String SubjectName) {
        this.SubjectName = SubjectName;
    }
    
}
