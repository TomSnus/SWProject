/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.service;

import java.io.Serializable;

/**
 * DTO for creating a Order Object
 * @author Tom
 */

public class OrderDTO implements Serializable {
    
   
    
    private Long amount;
    private String fuelType;
    private Long customerId;

    public OrderDTO(Long amount, String fuelType, Long customerId) {
        this.amount = amount;
        this.fuelType = fuelType;
        this.customerId = customerId;
    }

    public OrderDTO() {
    }
    
    

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    


    
}
