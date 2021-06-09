/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSFS.UnerBasicSchoolFinancialSystem.Term;
 import com.UBSFS.UnerBasicSchoolFinancialSystem.Academic.Academic;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author enock
 */

@Entity
public class Term {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String TermName;
        private String StartDate;
    private String EndDate;

    public Term() {
    }
    
    @ManyToOne
    private Academic academicyear;

    public Term(String TermName, String StartDate, String EndDate, Academic academicyear) {
        this.TermName = TermName;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.academicyear = academicyear;
    }
    

    public Academic getAcademicyear() {
        return academicyear;
    }

    public void setAcademicyear(Academic academicyear) {
        this.academicyear = academicyear;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTermName() {
        return TermName;
    }

    public void setTermName(String TermName) {
        this.TermName = TermName;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate;
    }
    
}
