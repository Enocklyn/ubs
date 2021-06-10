/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.security;

import com.UBSDB.UnerBasicSchoolFinancialSystem.Teacher.TeacherService1;
import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author enock
 */
@Controller
public class UserController {
    @Autowired
    private UserService UDSI;
    
    @Autowired 
    private UserRepository repo;
    @Autowired
    private TeacherService1 TS;
    
    @GetMapping("/RouteUser")
    public String showAddUserPage(Model model,Principal principal,Authentication authentication){
       
        Optional <? extends GrantedAuthority> role=authentication.getAuthorities().stream().findFirst();
   
        if(role.get().getAuthority().equals("Admin")){
     return "redirect:/";
   
      }
   if(role.get().getAuthority().equals("Teacher")){
       
   return "redirect:/TeacherPortal/"+TS.findbyStaffId(principal.getName()).getTeacherId();
   
      }
   if(role.get().getAuthority().equals("Student")){
   
   return "redirect:/StudentPortal/";
   
      }
   return "";
     }
    @PostMapping("/addUser")
    
    public String addUser(Model model,@Valid users user ){
    model.addAttribute("msg" + UDSI.addUser(user));
    return "index";    
    } 
    
    @GetMapping("/AddUserAdmin")
    public @ResponseBody users AddUser(){
    users user=new users();
             BCryptPasswordEncoder encode=new BCryptPasswordEncoder();
            
            user.setEnabled(true);
            user.setUsername("enock");
      
           user.setPassword(encode.encode("Enock1234"));
            role role=new role();
            role.setName("Admin");
          System.out.println("visited this place");
            
            Set<role>roles=new HashSet<>();
            roles.add(role);
           user.setRoles(roles);
           user= repo.save(user);
        return user;
    }
    @GetMapping("/login")
    public String ViewLogInPage(){
    
    return "Login";
    }
    @GetMapping("/error")
    public String ViewErrorInPage(){
    
    return "Login";
    } 
   
}
