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
 * @author Jon
 */
@NamedQueries({
    @NamedQuery(name="OCforwardingCompany.select",
                query="SELECT c FROM OCforwardingCompany AS c"),
})
@Entity
public class OCforwardingCompany extends OCcompany implements Serializable {

    public OCforwardingCompany(String name, OCaddress address) {
        super.setName(name);
        super.setAddress(address);
    }

    public OCforwardingCompany() {
    }
 
}
