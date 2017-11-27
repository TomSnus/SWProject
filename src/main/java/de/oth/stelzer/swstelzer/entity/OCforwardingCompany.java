/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.stelzer.swstelzer.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Jon
 */
@Entity
public class OCforwardingCompany extends OCsingleIdEntity implements Serializable {

      private String name;
    private String description;
    @ManyToOne
    private OCaddress address;
    
    
}
