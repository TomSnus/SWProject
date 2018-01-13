/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil CompanyModel
 */
package de.oth.stelzer.swstelzer.model;

import de.oth.stelzer.swstelzer.entity.OCaddress;
import de.oth.stelzer.swstelzer.entity.OCcompany;
import de.oth.stelzer.swstelzer.entity.OCcustomer;
import de.oth.stelzer.swstelzer.entity.OCforwardingCompany;
import de.oth.stelzer.swstelzer.service.CRMService;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Tom
 */
@Named
@SessionScoped
public class CompanyModel implements Serializable {

    private String name;
    private String description;
    //Address
    private String zip;
    private String state;
    private String street;
    private String streetNumber;

    private Map<? extends OCcompany, Boolean> checked = new HashMap<>();

    @Inject
    private CRMService crmService;

    public Collection<OCcustomer> getAllCustomers() {
        return this.crmService.getAllCustomers();
    }

    public String removeCustomers() {
        for (Map.Entry<? extends OCcompany, Boolean> entry : checked.entrySet()) {
            if (entry.getValue()) {
                crmService.removeCustomer((OCcustomer) entry.getKey());
            }
        }
        //clean checked list
        checked.clear();

        return "refresh";
    }

    public Collection<OCforwardingCompany> getAllForwardingCompanies() {
        return this.crmService.getAllForwardingCompanies();
    }

    public String removeForwardingCompanies() {
        for (Map.Entry<? extends OCcompany, Boolean> entry : checked.entrySet()) {
            if (entry.getValue()) {
                crmService.removeForwardingCompany((OCforwardingCompany) entry.getKey());
            }
        }

        //clean checked list
        checked.clear();

        return "refresh";
    }

    public String verifyCompany(String company) {
        OCaddress newAddress = new OCaddress();
        newAddress.setState(this.state);
        newAddress.setStreet(this.street);
        newAddress.setStreet(this.streetNumber);
        newAddress.setZip(this.zip);

        switch (company) {
            case "customer":
                return verifyCustomer(newAddress);
            case "fwc":
                return verifyFWC(newAddress);
            default:
                return "refresh";
        }
    }

    public String verifyFWC(OCaddress newAddress) {
        OCforwardingCompany newCompany = new OCforwardingCompany();
        newCompany.setName(this.name);
        newCompany.setAddress(newAddress);
        crmService.addCompany(newCompany);
        cleanAttributs();
        return "refresh";
    }

    public String verifyCustomer(OCaddress newAddress) {
        OCcustomer newCustomer = new OCcustomer();
        newCustomer.setName(this.name);
        newCustomer.setAddress(newAddress);
        crmService.addCompany(newCustomer);
        cleanAttributs();
        return "refresh";
    }

    private void cleanAttributs() {
        this.name = "";
        this.description = "";
        this.state = "";
        this.street = "";
        this.streetNumber = "";
        this.zip = null;
    }

    /**
     * Begin Getter & Setter
     */
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

    public Map<? extends OCcompany, Boolean> getChecked() {
        return checked;
    }

    public void setChecked(Map<OCcustomer, Boolean> checked) {
        this.checked = checked;
    }

    /**
     * End Getter & Setter
     */
}
