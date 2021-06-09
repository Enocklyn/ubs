/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSFS.UnerBasicSchoolFinancialSystem.SMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author enock
 */
@Controller
public class SmsController {
@Autowired
private SmsService sS;

  @GetMapping("ShowSendSmsForm") 
  public String ShowSendSmsForm(Model model){
  model.addAttribute("sms",new Sms());
  return "index";
  } 
  
  @PostMapping("/sendSms")
  public String SendSms(Model model,Sms sms){
      sms.setPhonenumber(sS.getParentPhonenumbers());
      SMSConfiguration config=new SMSConfiguration();
      config.setAccountSid("ACa601a6e7a962ec3205f816d6c511b2d3");
      config.setAuthToken("74f4fabbdd9f7d8404185195d8d5f077");
      config.setTrialNumber("+16785155448");
      model.addAttribute("msg",sS.sendSms(config,sms) );
      return "index";
  }
  
 
}
