/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.iface;

import de.oth.stelzer.swstelzer.entity.OCcompany;
import de.oth.stelzer.swstelzer.entity.OCcustomer;
import de.oth.stelzer.swstelzer.entity.OCforwardingCompany;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Tom
 */
public interface ICRMService {
    public OCcustomer getCustomerById(Long id);
    
    public List<OCcustomer> getAllCustomers();
    
    public void removeCustomer(OCcustomer customer);
    
    public Collection<OCforwardingCompany> getAllForwardingCompanies();
    
    public void removeForwardingCompany(OCforwardingCompany fwc);
    
    public OCcompany addCompany(OCcompany company);
    
    public OCforwardingCompany getForwardingCompanyById(Long id);
}
