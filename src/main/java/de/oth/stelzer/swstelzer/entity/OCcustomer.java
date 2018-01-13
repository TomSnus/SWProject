/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Tom
 */
@NamedQueries({
    @NamedQuery(name = "OCcustomer.select",
            query = "SELECT c FROM OCcustomer AS c"),})
@Entity
public class OCcustomer extends OCcompany implements Serializable {

    public OCcustomer() {
    }

    public OCcustomer(String name, OCaddress address) {
        super.setAddress(address);
        super.setName(name);
    }

}
