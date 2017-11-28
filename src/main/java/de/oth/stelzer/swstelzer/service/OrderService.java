/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.service;

import de.oth.stelzer.swstelzer.entity.OCfuel;
import de.oth.stelzer.swstelzer.entity.OCorder;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
    public Double getPrice(String ft){
        String queryParam = ft;
        TypedQuery query =  entityManager.createNamedQuery("OCfuel.getSingleFuel", OCfuel.class);
        query.setParameter("queryParam", queryParam);
        List<OCfuel> fuelList = query.getResultList();
        return fuelList.get(0).getPrice();
    }
    
     @Transactional
    public List<OCfuel> getAllPrices(){
        TypedQuery query = entityManager.createNamedQuery("OCfuel.getAll", OCfuel.class);
        List<OCfuel> fuelList = query.getResultList();
        System.out.println(fuelList);
        return fuelList;
       
    }
    
    @Transactional
    public void insertFuel(OCfuel fuel) {
        entityManager.persist(fuel);
    }
    
    @Transactional
    public void updateFuelPrice(long id, double price){
       Query query = entityManager.createNamedQuery("OCfuel.updatePrice");
       query.setParameter("queryparam", price).setParameter("queryparam2", id).executeUpdate();
    }
    
    @Transactional
    public OCorder order(OCorder order){
        entityManager.persist(order);
        return order;
    }
    
    @Transactional
    public List<OCorder> getAllOrders(){
        TypedQuery query = entityManager.createNamedQuery("OCorder.getAll", OCorder.class);
        return query.getResultList();
    }
}
