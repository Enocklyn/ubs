/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.FeePayment;

import com.UBSDB.UnerBasicSchoolFinancialSystem.Student.Student;
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
public class Payments {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;
    
private String paymentType;

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

@OneToOne
private Term term;

private double Amount;

private double Remaining; 

@OneToOne
private Student Student;

    public Student getStudent() {
        return Student;
    }

    public void setStudent(Student Student) {
        this.Student = Student;
    }
    public Long getId() {
        return id;
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

    public double getRemaining() {
        return Remaining;
    }

    public void setRemaining(double Remaining) {
        this.Remaining = Remaining;
    }
}
