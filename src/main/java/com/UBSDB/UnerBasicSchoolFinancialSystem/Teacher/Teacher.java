/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.Teacher;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author enock
 */
@Entity
public class Teacher {
   private String FirstName;

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

    public String getPhoneNumber() {
        return PhoneNumber;
    }
    private String StaffId;

    public String getStaffId() {
        return StaffId;
    }

    public void setStaffId(String StaffId) {
        this.StaffId = StaffId;
    }
    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }
    private String LastName;
     private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private Long TeacherId;
   
   private String PhoneNumber ;

    public String getPhoneNumberId() {
        return PhoneNumber;
    }

    public void setPhoneNumberId(String PhoneNumberId) {
        this.PhoneNumber = PhoneNumberId;
    }
   
   private String TeacherStatus;

    public String getTeacherStatus() {
        return TeacherStatus;
    }

    public void setTeacherStatus(String TeacherStatus) {
        this.TeacherStatus = TeacherStatus;
    }
    
   

    public Long getTeacherId() {
        return TeacherId;
    }

   
}
