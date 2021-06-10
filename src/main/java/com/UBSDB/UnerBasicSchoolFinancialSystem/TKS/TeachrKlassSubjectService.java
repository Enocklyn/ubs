/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.TKS;

import com.UBSDB.UnerBasicSchoolFinancialSystem.Subject.Subject;
import com.UBSDB.UnerBasicSchoolFinancialSystem.klass.klass;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author enock
 */
@Service
public class TeachrKlassSubjectService {
    @Autowired
    private TeachrKlassSubjectRepository TKSR;
    
    public String saveTeacherClassSubject(TeachrKlassSubject TKS){
    try{
    TKSR.save(TKS);
    return "Subject has been added successfully";
    }catch(Exception ex){
    return "error contact admin  ------>>>>"+ex.toString();
    }}
    
    public List<Subject> getTeacherClassSubject(Long teacherId,String klassId){
    List<Subject>TempSubject=new ArrayList<>();
        for(TeachrKlassSubject TKS:TKSR.findAll()){
    if((TKS.getKlas().getClassName().equals(klassId))&&(TKS.getTeacher().getTeacherId().equals(teacherId))){
    TempSubject.add(TKS.getSubjects());} 
    }
    return TempSubject;
 
    }
    public String Check_If_Subject_Exist_For_A_Class(Long Subject, String klass){
    for(TeachrKlassSubject TKS:TKSR.findAll()){
    if((TKS.getKlas().getClassName().equals(klass))&&(TKS.getSubjects().getId().equals(Subject))){
    
    return "exist";
    }
    }
    return "Not";
    }
            
    public Set<klass>get_Teacher_Klass(Long TeacherId){
        Set<klass>TeacherKlass=new HashSet<>();
    for(TeachrKlassSubject TKS:TKSR.findAll()){
    if(TKS.getTeacher().getTeacherId().equals(TeacherId)){
        
        TeacherKlass.add(TKS.getKlas());
        
    }}
    return TeacherKlass;
    }
    public String removeTeacher(Long TeacherId,Long SubjectId){
    try{
        for(TeachrKlassSubject tks:TKSR.findAll()){
        if(tks.getTeacher().getTeacherId().toString().equals(TeacherId.toString())&&((tks.getSubjects().getId().toString()).equals(SubjectId.toString()))){
        TKSR.delete(tks);
         return " successfully removed   ";
   
        }
        }
    }catch(Exception ex){
    
    return "Fatal Error contact admin "+ex.toString();
    }
    return "null";
    }
    public List<TeachrKlassSubject>getKlassSubjects(String klassId){
    List<TeachrKlassSubject>KlassSubjects=new ArrayList<>();
    for(TeachrKlassSubject TKS:TKSR.findAll()){
    if(TKS.getKlas().getClassName().equals(klassId)){
    
    KlassSubjects.add(TKS);
    }
    
    }
    return KlassSubjects;
    }
}
