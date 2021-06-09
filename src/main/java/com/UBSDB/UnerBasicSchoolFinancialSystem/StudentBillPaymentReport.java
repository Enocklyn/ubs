/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSFS.UnerBasicSchoolFinancialSystem;

import com.UBSFS.UnerBasicSchoolFinancialSystem.Bill.StudentBill;
import com.UBSFS.UnerBasicSchoolFinancialSystem.FeePayment.Payments;
import com.UBSFS.UnerBasicSchoolFinancialSystem.Student.Student;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author enock
 */
@Component
public class StudentBillPaymentReport {
    private Student stud;
    private Payments Studentpay;
    private StudentBill Studentbills;
    private double remainingAmount;
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Student getStud() {
        return stud;
    }

    public void setStud(Student stud) {
        this.stud = stud;
    }

    public Payments getStudentpay() {
        return Studentpay;
    }

    public void setStudentpay(Payments Studentpay) {
        this.Studentpay = Studentpay;
    }

    public StudentBill getStudentbills() {
        return Studentbills;
    }

    public void setStudentbills(StudentBill Studentbills) {
        this.Studentbills = Studentbills;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }
    
}
