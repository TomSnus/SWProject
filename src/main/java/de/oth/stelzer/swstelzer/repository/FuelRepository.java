/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.repository;

import de.oth.stelzer.swstelzer.entity.OCfuel;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Tom
 */
public class FuelRepository implements Repository<OCfuel>{
    
    //SQL Queries
    private final String getPriceQuery = "SELECT o FROM OCfuel o WHERE o.fuelType=:queryParam";
    private final String getAllPricesQuery = "SELECT o FROM OCfuel o";
    
    @PersistenceContext(unitName="SWStelzer_pu")
    private EntityManager entityManager;
    
    @Override
    public void add(OCfuel item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Iterable<OCfuel> items) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(OCfuel item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(OCfuel item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Specification specification) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OCfuel> query(Specification specification) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Double getPrice(String ft) {
        String queryParam = ft;
        TypedQuery query =  entityManager.createQuery(getPriceQuery, OCfuel.class);
        query.setParameter("queryParam", queryParam);
        List<OCfuel> fuelList = query.getResultList();
        return fuelList.get(0).getPrice();
    }

    public List<OCfuel> getAllPrices() {
        TypedQuery query = entityManager.createQuery(getAllPricesQuery, OCfuel.class);
        List<OCfuel> fuelList = query.getResultList();
        return fuelList;
    }

    public void insertFuel(OCfuel fuel) {
        entityManager.persist(fuel);
    }
    
}
