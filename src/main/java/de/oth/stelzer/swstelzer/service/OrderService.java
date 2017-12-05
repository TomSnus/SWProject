/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.service;

import de.oth.stelzer.swstelzer.entity.OCcustomer;
import de.oth.stelzer.swstelzer.entity.OCforwardingCompany;
import de.oth.stelzer.swstelzer.entity.OCfuel;
import de.oth.stelzer.swstelzer.entity.OCorder;
import de.oth.stelzer.swstelzer.entity.OCstatus;
import de.oth.stelzer.swstelzer.entity.OrderDTO;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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

    @Inject
    CRMService crmService;
    
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
    public OCorder createOrder(OrderDTO orderDTO){
        OCcustomer customer = crmService.getCustomerById(orderDTO.getCustomerId());
        OCfuel fuel = this.getFuelByType(orderDTO.getFuelType());
        OCforwardingCompany fwCompany = this.getForwardingCompanyById(43l);
        Date dateTime = new Date();
        OCstatus status = OCstatus.PROCESSING;
        String statusDescription = "Order in process";
        Double orderPrice = calcPrice(orderDTO.getAmount(), fuel);
        
        //TODO Aufruf Josef
        
        OCorder order = new OCorder();
        order.setFuel(fuel);
        order.setCustomer(customer);
        order.setForwardingCompany(fwCompany);
        order.setOrderDate(dateTime);
        order.setStatus(status);
        order.setStatusDescription(statusDescription);
        order.setOrderPrice(orderPrice);
        //TEST
        order.setTranspordId(154l);
        order.setAmount(orderDTO.getAmount());
        entityManager.persist(order);
        return order;
    }
    
    @Transactional
    public List<OCorder> getAllOrders(){
        TypedQuery query = entityManager.createNamedQuery("OCorder.getAll", OCorder.class);
        return query.getResultList();
    }
    
    @Transactional
    public String getStatusDescription(int id){
       TypedQuery query = entityManager.createNamedQuery("OCorder.getStatus", OCstatus.class);
       OCorder order = (OCorder) query.getSingleResult();
       return order.getStatusDescription();
    }
    
    @Transactional
    public void updateStatus(OCorder order, OCstatus status, String statusDesc){
         order.setStatus(status);
         order.setStatusDescription(statusDesc);
         entityManager.merge(order);
    }
    
    @Transactional
    private OCfuel getFuelByType(String fuelType) {
        TypedQuery<OCfuel> query = entityManager.createNamedQuery("OCfuel.getSingleFuel", OCfuel.class);
        query.setParameter("queryParam", fuelType);
        OCfuel fuel = query.getSingleResult();
        
        return fuel;
    }
    
    @Transactional
    private OCforwardingCompany getForwardingCompanyById(Long id) {
        return entityManager.find(OCforwardingCompany.class, id);
    }

    @Transactional
    private Double calcPrice(Long amount, OCfuel fuel) {
        return amount * fuel.getPrice();
    }

  
}
