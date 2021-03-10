/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSFS.UnerBasicSchoolFinancialSystem.SchoolFees;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Academic.Academic;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Student.Student;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Term.Term;
import com.UBSFS.UnerBasicSchoolFinancialSystem.klass.klass;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author enock
 */
@Entity
public class SchoolFees {
 @Id
 @GeneratedValue(strategy=GenerationType.AUTO)
 private Long Id;
 
 @OneToOne
  private Term term;

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

   

 private LocalDate FeesDate;
 
 private double Amount;
 
 private String FeeSpecification;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public LocalDate getFeesDate() {
        return FeesDate;
    }

    public void setFeesDate(LocalDate FeesDate) {
        this.FeesDate = FeesDate;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public String getFeeSpecification() {
        return FeeSpecification;
    }

    public void setFeeSpecification(String FeeSpecification) {
        this.FeeSpecification = FeeSpecification;
    }
 
 
}
