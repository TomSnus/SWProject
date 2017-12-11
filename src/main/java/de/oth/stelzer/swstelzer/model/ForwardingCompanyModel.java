/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.model;

import de.oth.stelzer.swstelzer.entity.OCaddress;
import de.oth.stelzer.swstelzer.entity.OCforwardingCompany;
import de.oth.stelzer.swstelzer.service.CRMService;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Tom
 */
@Named(value="fwcModel")
@SessionScoped
public class ForwardingCompanyModel implements Serializable{

    private String name;

    //Address
    private String zip;
    private String state;
    private String street;
    private String streetNumber;

    private Map<OCforwardingCompany, Boolean> checked = new HashMap<>();

    @Inject
    private CRMService crmService;

    public Collection<OCforwardingCompany> getAllForwardingCompanies() {
        return this.crmService.getAllForwardingCompanies();
    }

    public String removeForwardingCompanies() {
        for (Map.Entry<OCforwardingCompany, Boolean> entry : checked.entrySet()) {
            if (entry.getValue()) {
                crmService.removeForwardingCompany(entry.getKey());
            }
        }

        //clean checked list
        checked.clear();

        return "fwc_index.xhtml";
    }

    public String verifyCustomer() {
        OCaddress newAddress = new OCaddress();
        newAddress.setState(this.state);
        newAddress.setStreet(this.street);
        newAddress.setStreet(this.streetNumber);
        newAddress.setZip(this.zip);

        OCforwardingCompany newCompany = new OCforwardingCompany();
        newCompany.setName(this.name);
        newCompany.setAddress(newAddress);

        if (!newCompany.getName().equals("")) {
            crmService.addForwardingCompany(newCompany);
            cleanAttributs();
        }

        return "fwc_index.xhtml";
    }

    private void cleanAttributs() {
        this.name = "";
        this.state = "";
        this.street = "";
        this.streetNumber = "";
        this.zip = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Map<OCforwardingCompany, Boolean> getChecked() {
        return checked;
    }

    public void setChecked(Map<OCforwardingCompany, Boolean> checked) {
        this.checked = checked;
    }
    
    
    
}
