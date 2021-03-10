/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSFS.UnerBasicSchoolFinancialSystem.Bill;

import com.UBSFS.UnerBasicSchoolFinancialSystem.PTA.PTA;
import com.UBSFS.UnerBasicSchoolFinancialSystem.SchoolFees.SchoolFees;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Student.Student;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Term.Term;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author enock
 */
@Entity
@Table(name="bill")
public class StudentBill {
 @Id
 @GeneratedValue(strategy=GenerationType.AUTO)
 private Long Id;   
 
 @OneToOne
 private PTA pta;  

 @OneToOne
private  SchoolFees fee;

 @OneToOne
 private Term term;


    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public PTA getPta() {
        return pta;
    }

    public void setPta(PTA pta) {
        this.pta = pta;
    }

    public SchoolFees getFee() {
        return fee;
    }

    public void setFee(SchoolFees fee) {
        this.fee = fee;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

   

}
