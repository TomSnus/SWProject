/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.model;

import de.oth.stelzer.swstelzer.entity.OCcustomer;
import de.oth.stelzer.swstelzer.entity.OCforwardingCompany;
import de.oth.stelzer.swstelzer.entity.OCfuel;
import de.oth.stelzer.swstelzer.entity.OCorder;
import de.oth.stelzer.swstelzer.entity.OCstatus;
import de.oth.stelzer.swstelzer.service.OrderService;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Tom
 */
@Named(value = "orderModel")
@SessionScoped
public class OrderModel implements Serializable {

    //Attributes
    private Long amount;
    private Long transpordId;
    private Date orderDate;
    private String statusDescription;
    private Double orderPrice;
    private OCcustomer customer;
    private OCfuel fuel;
    private OCstatus status;
    private OCforwardingCompany forwardingCompany;

    @Inject
    private OrderService orderService;

    public Collection<OCorder> allOrders() {
        return this.orderService.getAllOrders();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getTranspordId() {
        return transpordId;
    }

    public void setTranspordId(Long transpordId) {
        this.transpordId = transpordId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public OCcustomer getCustomer() {
        return customer;
    }

    public void setCustomer(OCcustomer customer) {
        this.customer = customer;
    }

    public OCfuel getFuel() {
        return fuel;
    }

    public void setFuel(OCfuel fuel) {
        this.fuel = fuel;
    }

    public OCstatus getStatus() {
        return status;
    }

    public void setStatus(OCstatus status) {
        this.status = status;
    }

    public OCforwardingCompany getForwardingCompany() {
        return forwardingCompany;
    }

    public void setForwardingCompany(OCforwardingCompany forwardingCompany) {
        this.forwardingCompany = forwardingCompany;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

}
