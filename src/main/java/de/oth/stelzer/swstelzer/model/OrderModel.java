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
import de.oth.stelzer.swstelzer.iface.IOrderService;
import de.oth.stelzer.swstelzer.service.OrderService;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
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
    private Long transportId;
    private Date orderDate;
    private String statusDescription;
    private Double orderPrice;
    private OCcustomer customer;
    private OCfuel fuel;
    private OCstatus status;
    private OCforwardingCompany forwardingCompany;

    private String errorMsg;

    @Inject
    private IOrderService orderService;
    private String statusText;

    public Collection<OCorder> allOrders() {
        return this.orderService.getAllOrders();
    }

    /**
     * Begin Getter & Setter 
     */
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getTransportId() {
        return transportId;
    }

    public void setTransportId(Long transportId) {
        this.transportId = transportId;
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

    public IOrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    
    public String getStatusText() {
        return statusText;
    }
    
    /**
     * End Getter & Setter 
     */

    public void checkStatus() {
        OCorder order = this.orderService.getStatusDescription(this.transportId);
        if (order != null) {
            this.amount = order.getAmount();
            this.customer = order.getCustomer();
            this.forwardingCompany = order.getForwardingCompany();
            this.fuel = order.getFuel();
            this.orderDate = order.getOrderDate();
            this.status = order.getStatus();
            this.orderPrice = order.getOrderPrice();
            this.statusDescription = order.getStatusDescription();
            this.errorMsg = "";
            setStatusText();
        } else {
            errorMsg = "No Order found for the ID: " + this.transportId;
            clearAttributes();
        }

    }

    private void clearAttributes() {
        this.amount = 0l;
        this.customer = null;
        this.forwardingCompany = null;
        this.fuel = null;
        this.orderDate = null;
        this.orderPrice = 0d;
        this.status = null;
        this.statusDescription = null;
    }

    public void setStatusText() {
        statusText = "Your order with the ID: " + transportId + " is in the Status: " + this.statusDescription + "\n"
                + "Orderdate: " + this.orderDate + "\n"
                + "Amount: " + this.amount + " liter " + this.fuel.getFuelType() + "\n"
                + "Price: " + this.orderPrice + " €";

    }
}
