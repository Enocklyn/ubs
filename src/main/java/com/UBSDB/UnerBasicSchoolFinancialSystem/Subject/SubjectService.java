/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.Subject;

import com.UBSDB.UnerBasicSchoolFinancialSystem.klass.klass;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author enock
 */
@Service
public class SubjectService {
    @Autowired
    private SubjectRepository Subjectrepo;
    
    public String saveSubject(Subject subject){
    return Subjectrepo.save(subject)+" saved Successfully";
    
    }
    
    public List<Subject>Allsubjects(){
    return Subjectrepo.findAll();
    }
    
    public Subject findAParticularSubject(Long id){
    return Subjectrepo.findById(id).get();
    }
    
    public List findSubjectDoneByAParticularClass(String klassId){
    List<Subject>TempSubject=new ArrayList<>();
        for(Subject sub:Subjectrepo.findAll()){
    for(klass klass:sub.getKlaases()){
    if(klass.getClassName().equals(klassId)){
    TempSubject.add(sub);
    
    }
    
    }
    
    }
    return TempSubject;
    }
}
