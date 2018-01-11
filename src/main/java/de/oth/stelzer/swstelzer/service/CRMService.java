/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.service;

import de.oth.stelzer.swstelzer.entity.OCaddress;
import de.oth.stelzer.swstelzer.entity.OCcompany;
import de.oth.stelzer.swstelzer.entity.OCcustomer;
import de.oth.stelzer.swstelzer.entity.OCforwardingCompany;
import java.util.Collection;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author Tom
 */
@WebService(serviceName = "CRMService")
@RequestScoped
public class CRMService {
    
    @PersistenceContext(unitName = "SWStelzer_pu")
    private EntityManager entityManager;
    
    @Transactional
    @WebMethod(exclude=true)
    public OCcustomer getCustomerById(Long id) {
        return entityManager.find(OCcustomer.class, id);
    }
    
    @Transactional
    @WebMethod(exclude = true)
    public List<OCcustomer> getAllCustomers() {
        TypedQuery<OCcustomer> query = entityManager.createNamedQuery("OCcustomer.select", OCcustomer.class);
        List<OCcustomer> list = query.getResultList();
        
        return list;
    }
    
    @Transactional
    @WebMethod(exclude=true)
    public void removeCustomer(OCcustomer customer) {
        customer = entityManager.merge(customer);
        OCaddress address = entityManager.merge(customer.getAddress());
        
        entityManager.remove(customer);
        entityManager.remove(address);
        
        
    }
    @Transactional
    @WebMethod(exclude=true)
    public Collection<OCforwardingCompany> getAllForwardingCompanies() {
        TypedQuery<OCforwardingCompany> query = entityManager.createNamedQuery("OCforwardingCompany.select", OCforwardingCompany.class);
        List<OCforwardingCompany> list = query.getResultList();
        
        return list;
    }
    @Transactional
    @WebMethod(exclude=true)
    public void removeForwardingCompany(OCforwardingCompany fwc) {
        fwc = entityManager.merge(fwc);
        OCaddress address = entityManager.merge(fwc.getAddress());
        entityManager.remove(fwc);
        entityManager.remove(address);
    }

    @Transactional
    @WebMethod(exclude=true)
    public OCcompany addCompany(OCcompany company) {
        entityManager.persist(company);
        return company;
    }
    
}
