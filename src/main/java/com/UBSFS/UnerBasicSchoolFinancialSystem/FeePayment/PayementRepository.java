/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSFS.UnerBasicSchoolFinancialSystem.FeePayment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

/**
 *
 * @author enock
 */
@Controller
public interface PayementRepository extends JpaRepository<Payments,Long> {
    
}
