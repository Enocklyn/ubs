/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.SMS;

import com.UBSDB.UnerBasicSchoolFinancialSystem.Student.Student;
import com.UBSDB.UnerBasicSchoolFinancialSystem.Student.StudentService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author enock
 */
@Service
public class SmsService {
@Autowired
private StudentService SS;
public List<String>getParentPhonenumbers(){
    List<String>TemPhonenumbers = new ArrayList<>();
for(Student stud:SS.students()){
TemPhonenumbers.add("+233"+stud.getParentNumber());

}
return TemPhonenumbers;
}
    public String sendSms(SMSConfiguration Config,Sms sms){
        
    try{
       Twilio.init(Config.getAccountSid(),Config.getAuthToken());
    
    for(String number:sms.getPhonenumber()){
   
   Message.creator( new PhoneNumber(number),new PhoneNumber( Config.getTrialNumber()), sms.getMessage()).create();
   }return "message sent successfily";
    
    }catch(Exception ex){
        return "fata Error"+ex.toString();

    }
   }
  }