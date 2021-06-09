/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.Academic;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Term.Term;
import java.io.Serializable;
import java.time.LocalDate;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author enock
 */

@Entity
public class Academic implements Serializable {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long Id;
  private LocalDate AcademicYear;
    
   @OneToMany(mappedBy="academicyear")
   private Set <Term>term; 

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public LocalDate getAcademicYear() {
        return AcademicYear;
    }

    public void setAcademicYear(LocalDate AcademicYear) {
        this.AcademicYear = AcademicYear;
    }

    public Set<Term> getTerm() {
        return term;
    }

    public void setTerm(Set<Term> term) {
        this.term = term;
    }

   
}
