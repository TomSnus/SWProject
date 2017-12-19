/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.stelzer.swstelzer.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Tom
 */
@NamedQueries({
    @NamedQuery(name="OCcustomer.select",
                query="SELECT c FROM OCcustomer AS c"),
})
@Entity
public class OCcustomer extends OCcompany implements Serializable {
    
//    private String description;
//
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }


//    public OCcustomer(String name, String description, OCaddress address) {
//        super.setName(name);
//        this.description = description;
//        super.setAddress(address);
//    }

    public OCcustomer() {
    }
    
    public  OCcustomer(String name, OCaddress address) {
        super.setAddress(address);
        super.setName(name);
    }
    
    
    
}
