/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.service;

import de.oth.stelzer.swstelzer.entity.OCcustomer;
import de.oth.stelzer.swstelzer.entity.OCorder;
import javax.enterprise.context.RequestScoped;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author Tom
 */

@WebService(serviceName="OrderService")
@RequestScoped
public class OrderService {
    @PersistenceContext(unitName="SWStelzer_pu")
    private EntityManager entityManager;
    
    
    
    @Transactional
    public OCorder order(OCorder order){
       entityManager.persist(order);
       return order;
    }
    
}
