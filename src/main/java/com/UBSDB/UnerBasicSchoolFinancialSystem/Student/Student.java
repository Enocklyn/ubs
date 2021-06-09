/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.UBSFS.UnerBasicSchoolFinancialSystem.Student;

import com.UBSFS.UnerBasicSchoolFinancialSystem.Bill.StudentBill;
import com.UBSFS.UnerBasicSchoolFinancialSystem.klass.klass;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author enock
 */
@Entity
public class Student implements Serializable {
    @Id
    private String StudentId;
    private String FirstName;
    private String LastName;
    private String surname;
    private String dateOfBirth;
    private String physicalDisability;
    private String NHISCardumber;
    private String GuadiaName;
    //Staff or non-Staff
    private String staffId;
    private String ParentCategory;
    private String parentNumber;
    private String residentialAddress;
    private String Occupation;
    
    @OneToOne
    private klass studentklass;

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhysicalDisability() {
        return physicalDisability;
    }

    public void setPhysicalDisability(String physicalDisability) {
        this.physicalDisability = physicalDisability;
    }

    public String getNHISCardumber() {
        return NHISCardumber;
    }

    public void setNHISCardumber(String NHISCardumber) {
        this.NHISCardumber = NHISCardumber;
    }

    public String getGuadiaName() {
        return GuadiaName;
    }

    public void setGuadiaName(String GuadiaName) {
        this.GuadiaName = GuadiaName;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getParentCategory() {
        return ParentCategory;
    }

    public void setParentCategory(String ParentCategory) {
        this.ParentCategory = ParentCategory;
    }

    public String getParentNumber() {
        return parentNumber;
    }

    public void setParentNumber(String parentNumber) {
        this.parentNumber = parentNumber;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String Occupation) {
        this.Occupation = Occupation;
    }
    
    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
     @JoinTable(
            name = "stud_Bill",
              joinColumns = @JoinColumn(name = "StudentId"),
            inverseJoinColumns = @JoinColumn(name = "Id") )
    private  List<StudentBill> bill;

    public List<StudentBill> getBill() {
        return bill;
    }

    public void setBill(List<StudentBill> bill) {
        this.bill = bill;
    }
    
    
    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String StudentId) {
        this.StudentId = StudentId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public klass getStudentklass() {
        return studentklass;
    }

    public void setStudentklass(klass studentklass) {
        this.studentklass = studentklass;
    }
    
    
}
