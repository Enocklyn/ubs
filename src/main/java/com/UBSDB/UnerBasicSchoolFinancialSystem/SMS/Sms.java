/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSFS.UnerBasicSchoolFinancialSystem.SMS;

import com.twilio.Twilio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 *
 * @author enock
 */
@Component
public class Sms{

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(List<String> Phonenumber) {
        this.Phonenumber = Phonenumber;
    }
public String message;
public List<String>Phonenumber;

 
}
 