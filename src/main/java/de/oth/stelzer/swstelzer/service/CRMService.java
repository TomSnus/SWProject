/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.service;

import de.oth.stelzer.swstelzer.entity.OCcustomer;
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
    private List<OCcustomer> items;

    public OCcustomer getUserById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OCcustomer getCustomerById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<OCcustomer> getAllCustomers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    public void addCustomer(OCcustomer item) {
        entityManager.persist(item);
    }
        
    
}
