/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.stelzer.swstelzer.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Tom
 */

@NamedQueries({
    @NamedQuery(name="OCorder.getAll",
                query="SELECT o FROM OCorder o"),
    @NamedQuery(name="OCorder.getStatus",
                query="SELECT o FROM OCorder o WHERE o.statusDescription=:queryParam") 
          
})

@Entity
public class OCorder extends OCsingleIdEntity implements Serializable {
    
    private Long amount;
    private Long transpordId;
    private Date orderDate;
    private String statusDescription;
    private Double orderPrice;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private OCcustomer customer;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private OCfuel fuel;
    private OCstatus status;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private OCforwardingCompany forwardingCompany;
    

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



    public OCorder() {
    }
    
    
}
