/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.Excel;

import org.springframework.stereotype.Component;

/**
 *
 * @author enock
 */
@Component
public class StudentBillingFinanceExcel {
private String studentId;
private String Names;
private double OutstandingBalace;
private String StudentClass;

    public String getStudentClass() {
        return StudentClass;
    }

    public void setStudentClass(String StudentClass) {
        this.StudentClass = StudentClass;
    }
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getNames() {
        return Names;
    }

    public void setNames(String Names) {
        this.Names = Names;
    }

    public double getOutstandingBalace() {
        return OutstandingBalace;
    }

    public void setOutstandingBalace(double OutstandingBalace) {
        this.OutstandingBalace = OutstandingBalace;
    }

}
