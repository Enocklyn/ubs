/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.UBSDB.UnerBasicSchoolFinancialSystem.security;

/**
 *
 * @author enock
 *
    // remaining getters and setters   
}
 */
import javax.persistence.*;
 
@Entity
@Table(name = "roles")
public class role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
     
    private String name;
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
 