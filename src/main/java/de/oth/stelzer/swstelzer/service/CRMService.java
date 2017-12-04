/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.service;

import de.oth.stelzer.swstelzer.entity.OCcustomer;
import de.oth.stelzer.swstelzer.entity.OCforwardingCompany;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author Tom
 */

@WebService(serviceName="CRMService")
@RequestScoped
public class CRMService {
    
    @PersistenceContext(unitName="SWStelzer_pu")
    private EntityManager entityManager;
    
    @Transactional
    public OCcustomer getCustomerById(Long id) {
       return entityManager.find(OCcustomer.class, id);
    }
    
    
    @Transactional
    public List<OCcustomer> getAllCustomers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    public OCcustomer addCustomer(OCcustomer item) {
        entityManager.persist(item);
        return item;
    }
    
    @Transactional
    public OCforwardingCompany addForwardingCompany(OCforwardingCompany item) {
        entityManager.persist(item);
        return item;
    }
    
    @Transactional
    public void remove(OCcustomer item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    
}
