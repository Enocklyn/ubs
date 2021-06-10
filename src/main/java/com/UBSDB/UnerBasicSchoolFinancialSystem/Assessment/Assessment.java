/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.Assessment;

import com.UBSDB.UnerBasicSchoolFinancialSystem.Student.Student;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Subject.Subject;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Term.Term;
import java.io.Serializable;
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
public class Assessment implements Serializable {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getTestScores() {
        return TotalScore;
    }



    public double getExamScores() {
        return ExamScores;
    }

    public void setExamScores(double ExamScores) {
        this.ExamScores = ExamScores;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public double getTotalTestScore() {
        return TotalTestScore;
    }

    public void setTotalTestScore(double TotalTestScore) {
        this.TotalTestScore = TotalTestScore;
    }
@OneToOne
private  Student student;
  
private double TotalScore;

    public double getTotalScore() {
        return TotalScore;
    }

    public void setTotalScore(double TotalScore) {
        this.TotalScore = TotalScore;
    }
  
private double ExamScores;
@OneToOne
private Subject subject;

    public double getTotalTestScoreAverage() {
        return TotalTestScoreAverage;
    }

    public void setTotalTestScoreAverage(double TotalTestScoreAverage) {
        this.TotalTestScoreAverage = TotalTestScoreAverage;
    }
private double TotalTestScore;
private double TotalTestScoreAverage;
private double ExamsAverage;

    public double getExamsAverage() {
        return ExamsAverage;
    }

    public void setExamsAverage(double ExamsAverage) {
        this.ExamsAverage = ExamsAverage;
    }

    public double getTestScoreAverage() {
        return TestScoreAverage;
    }

    public void setTestScoreAverage(double TestScoreAverage) {
        this.TestScoreAverage = TestScoreAverage;
    }
private double TestScoreAverage;
@OneToOne
private Term term;


}
