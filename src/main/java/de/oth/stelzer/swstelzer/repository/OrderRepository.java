/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.repository;

import de.oth.stelzer.swstelzer.entity.OCorder;
import de.oth.stelzer.swstelzer.service.CRMService;
import de.oth.stelzer.swstelzer.service.OrderService;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tom
 */
public class OrderRepository implements Repository<OCorder>{
    @PersistenceContext(unitName="SWStelzer_pu")
    private EntityManager entityManager;

    
    @Override
    public void add(OCorder item) {
        entityManager.persist(item);
    }

    @Override
    public void add(Iterable<OCorder> items) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(OCorder item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(OCorder item) {
        entityManager.remove(item);
    }

    @Override
    public void remove(Specification specification) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OCorder> query(Specification specification) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
