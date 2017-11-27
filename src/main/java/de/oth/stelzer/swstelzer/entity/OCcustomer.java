/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.stelzer.swstelzer.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Tom
 */
@Entity
public class OCcustomer extends OCsingleIdEntity implements Serializable {
    
    private String name;
    private String description;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private OCaddress address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OCaddress getAddress() {
        return address;
    }

    public void setAddress(OCaddress address) {
        this.address = address;
    }

    public OCcustomer(String name, String description, OCaddress address) {
        this.name = name;
        this.description = description;
        this.address = address;
    }

    public OCcustomer() {
    }
    
    
    
    
    
}
