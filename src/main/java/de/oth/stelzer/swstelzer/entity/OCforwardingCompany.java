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
 * @author Jon
 */
@NamedQueries({
    @NamedQuery(name = "OCforwardingCompany.select",
            query = "SELECT c FROM OCforwardingCompany AS c"),})
@Entity
public class OCforwardingCompany extends OCcompany implements Serializable {

    public OCforwardingCompany(String name, OCaddress address) {
        super.setName(name);
        super.setAddress(address);
    }

    public OCforwardingCompany() {
    }

}
