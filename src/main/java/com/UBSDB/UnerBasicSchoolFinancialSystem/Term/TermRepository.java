/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.Term;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author enock
 */
@Repository
public interface TermRepository extends JpaRepository<Term,Long>{
    
}
