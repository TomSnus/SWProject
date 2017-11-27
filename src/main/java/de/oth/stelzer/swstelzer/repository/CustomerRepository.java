/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.repository;

import de.oth.stelzer.swstelzer.entity.OCcustomer;
import de.oth.stelzer.swstelzer.service.CRMService;
import de.oth.stelzer.swstelzer.service.OrderService;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Tom
 */
public class CustomerRepository implements Repository<OCcustomer>{
    @Inject
    CRMService crmService;
    
    
    public CustomerRepository(){
        
    }
    
    @Override
    public void add(OCcustomer item) {
        crmService.addCustomer(item);
    }

    @Override
    public void add(Iterable<OCcustomer> items) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(OCcustomer item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(OCcustomer item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Specification specification) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OCcustomer> query(Specification specification) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
