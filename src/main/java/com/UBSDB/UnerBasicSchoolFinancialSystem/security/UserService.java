/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author enock
 */
@Service
public class UserService {
    
     @Autowired
    private UserRepository userRepository;
     
  public String encode(String password){
BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
    return encoder.encode(password);
    }
   
    
    
    public users FindUserById(String id){
    return userRepository.getUserByUsername(id);
    
    }
    public String addUser(users user){
  
       userRepository.save(user);
   return"Successfully added "+user.getUsername() +"added";
   
   }
   
 
  public String addRoleTOUser(String Userid,role newRole){
      Set<role>userRoles=new HashSet<>();
      userRoles.add(newRole);
  users user=FindUserById( Userid);
  user.setRoles(userRoles);
  try{
      
  user=userRepository.save(user);
  return "user roles"+user.getRoles().toString();
  }catch(Exception ex){
  return"error adding role";
  }
 
  }   
}
