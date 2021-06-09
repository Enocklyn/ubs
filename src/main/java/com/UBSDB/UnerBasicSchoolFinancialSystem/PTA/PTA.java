/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.PTA;

import com.UBSDB.UnerBasicSchoolFinancialSystem.Term.Term;
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
public class PTA {

    public Long getId() {
        return id;
    }
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private double Amount;
  @OneToOne
    private Term term;

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }
    
}
