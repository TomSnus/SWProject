/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.service;

import de.oth.stelzer.swstelzer.delivery.DeliveryOrder;
import de.oth.stelzer.swstelzer.entity.OCcustomer;
import de.oth.stelzer.swstelzer.entity.OCforwardingCompany;
import de.oth.stelzer.swstelzer.entity.OCfuel;
import de.oth.stelzer.swstelzer.entity.OCorder;
import de.oth.stelzer.swstelzer.entity.OCstatus;
import de.oth.stelzer.swstelzer.resources.Environment;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Tom
 */
@WebService(serviceName = "OrderService")
@RequestScoped
public class OrderService {

    Environment environment = TestDeliveryService.environment;

    @PersistenceContext(unitName = "SWStelzer_pu")
    private EntityManager entityManager;

    @Inject
    CRMService crmService;

    @Inject
    DeliveryService delService;

    @Inject
    TestDeliveryService testDelService;

    /**
     *
     * @param ft
     * @return Fuel selected by Type
     */
    @Transactional
    @WebMethod(exclude = true)
    public OCfuel getFuelByType(String ft) {
        String queryParam = ft;
        TypedQuery query = entityManager.createNamedQuery("OCfuel.getSingleFuel", OCfuel.class);
        query.setParameter("queryParam", queryParam);
        List<OCfuel> fuelList = query.getResultList();
        return fuelList.get(0);
    }

    /**
     * Delivers all available Fuels
     *
     * @return Collection, containing all Fuels
     */
    @Transactional
    public Collection<OCfuel> getAllFuels() {
        TypedQuery query = entityManager.createNamedQuery("OCfuel.getAll", OCfuel.class);
        List<OCfuel> fuelList = query.getResultList();
        return fuelList;
    }

    /**
     * Insert new Fuel
     *
     * @param fuel
     */
    @Transactional
    @WebMethod(exclude = true)
    public void insertFuel(OCfuel fuel) {
        entityManager.persist(fuel);
    }

    /**
     * Update Fuel
     *
     * @param fuel
     */
    @Transactional
    @WebMethod(exclude = true)
    public void updateFuelPrice(OCfuel fuel) {
        entityManager.merge(fuel);
    }

    /**
     *
     * @param fdto
     * @return Fuel selected by DTO
     */
    @Transactional
    public OCfuel getFuelByDTO(FuelDTO fdto) {
        List<OCfuel> fuelList = null;
        try {
            String queryParam = fdto.getFueltype();
            TypedQuery query = entityManager.createNamedQuery("OCfuel.getSingleFuel", OCfuel.class);
            query.setParameter("queryParam", queryParam);
            fuelList = query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Fueltype not found", e);
        }
        return fuelList.get(0);

    }

    /**
     * ----Main Function for Control Flow---
     *
     * @param orderDTO
     * @return order
     */
    @Transactional
    public OCorder createOrder(@WebParam(name = "orderDTO") OrderDTO orderDTO) {
        OCorder order = new OCorder();
        try {
            //Create Attributes of order
            OCcustomer customer = crmService.getCustomerById(orderDTO.getCustomerId());
            OCfuel fuel = this.getFuelByType(orderDTO.getFuelType());
            OCforwardingCompany fwCompany = this.getForwardingCompanyById(43l);
            Date dateTime = new Date();
            OCstatus status = OCstatus.PROCESSING;
            String statusDescription = status.name();
            Double orderPrice = calcPrice(orderDTO.getAmount(), fuel);
            // Call Partner or Test environment
            DeliveryOrder result = null;
            if (environment.equals(Environment.PROD)) {
                result = delService.createDeliveryorder(customer, orderDTO);
            } else if (environment.equals(Environment.TEST)) {
                result = testDelService.createDeliveryorder(customer, orderDTO);
            }
            //Set Attributes of order
            order.setTransportId(result.getId());
            order.setOrderDate(dateTime);
            order.setStatus(status);
            order.setStatusDescription(statusDescription);
            order.setOrderPrice(orderPrice);
            order.setAmount(orderDTO.getAmount());
            order.setFuel(fuel);
            order.setCustomer(customer);
            order.setForwardingCompany(fwCompany);
            //Persist order
            entityManager.persist(customer);
            entityManager.persist(fwCompany);
            entityManager.persist(fuel);
            entityManager.persist(order);
        } catch (Exception e) {
            throw new RuntimeException("Order could not be created", e);
        }
        return order;

    }

    /**
     *
     * @return list of orders
     */
    @Transactional
    @WebMethod(exclude = true)
    public Collection<OCorder> getAllOrders() {
        TypedQuery query = entityManager.createNamedQuery("OCorder.getAll", OCorder.class);
        return query.getResultList();
    }

    /**
     *
     * @param transportId
     * @return order by transport ID (for Status purposes)
     */
    @Transactional
    @WebMethod(exclude = true)
    public OCorder getStatusDescription(long transportId) {
        OCorder order = null;
        try {
            TypedQuery query = entityManager.createNamedQuery("OCorder.getStatus", OCorder.class);
            query.setParameter("queryParam", transportId);
            order = (OCorder) query.getResultList().get(0);
        } catch (Exception ex) {
            //Element not found
        }
        return order;

    }

    /**
     * Updating order status
     * @param order
     * @param status
     * @param statusDesc
     */
    @Transactional
    @WebMethod(exclude = true)
    public void updateStatus(OCorder order, OCstatus status, String statusDesc) {
        order.setStatus(status);
        order.setStatusDescription(statusDesc);
        entityManager.merge(order);
    }

    @Transactional
    @WebMethod(exclude = true)
    private OCforwardingCompany getForwardingCompanyById(Long id) {
        return entityManager.find(OCforwardingCompany.class, id);
    }

    @Transactional
    @WebMethod(exclude = true)
    private Double calcPrice(Long amount, OCfuel fuel) {
        Double value = amount * fuel.getPrice();
        return (double) Math.round(value * 100) / 100; //output with 2 decimal places
    }

    /**
     * Remove fuel
     * @param item
     */
    @Transactional
    @WebMethod(exclude = true)
    public void removeFuel(OCfuel item) {
        item = entityManager.merge(item);
        entityManager.remove(item);
    }

}
