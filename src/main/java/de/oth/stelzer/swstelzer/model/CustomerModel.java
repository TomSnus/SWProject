/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.model;

import de.oth.stelzer.swstelzer.entity.OCaddress;
import de.oth.stelzer.swstelzer.entity.OCcustomer;
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
@Named 
@SessionScoped
public class CustomerModel implements Serializable{

    private String name;
    private String description;
    //Address
    private String zip;
    private String state;
    private String street;
    private String streetNumber;

    private Map<OCcustomer, Boolean> checked = new HashMap<>();

    @Inject
    private CRMService crmService;

    public Collection<OCcustomer> getAllCustomers() {
        return this.crmService.getAllCustomers();
    }

    public String removeCustomers() {
        for (Map.Entry<OCcustomer, Boolean> entry : checked.entrySet()) {
            if (entry.getValue()) {
                crmService.removeCustomer(crmService.getCustomerById(entry.getKey().getId()));
            }
        }

        //clean checked list
        checked.clear();

        return "customers_index.xhtml";
    }

    public String verifyCustomer() {
        OCaddress newAddress = new OCaddress();
        newAddress.setState(this.state);
        newAddress.setStreet(this.street);
        newAddress.setStreet(this.streetNumber);
        newAddress.setZip(this.zip);

        OCcustomer newCustomer = new OCcustomer();
        newCustomer.setName(this.name);
        newCustomer.setDescription(this.description);
        newCustomer.setAddress(newAddress);

        if (!newCustomer.getName().equals("")) {
            crmService.addCustomer(newCustomer);
            cleanAttributs();
        }

        return "customers_index.xhtml";
    }

    private void cleanAttributs() {
        this.name = "";
        this.description = "";
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Map<OCcustomer, Boolean> getChecked() {
        return checked;
    }

    public void setChecked(Map<OCcustomer, Boolean> checked) {
        this.checked = checked;
    }
    
    
    
}
