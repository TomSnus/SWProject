/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.entity;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Tom
 */
@Entity
public class OCaddress extends OCsingleIdEntity implements Serializable {

    private String zip;
    private String houseNumber;
    private String street;
    private String state;

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public OCaddress() {
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public OCaddress(String houseNumber, String street, String state) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.state = state;
    }

}
