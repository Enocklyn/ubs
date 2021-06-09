/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.SchoolFees;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Term.Term;
import java.io.Serializable;

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
public class SchoolFees implements Serializable {
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
