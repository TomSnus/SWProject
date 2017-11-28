/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.oth.stelzer.swstelzer.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Tom
 */
@NamedQueries({
    @NamedQuery(name="OCfuel.getAll",
                query="SELECT o FROM OCfuel o"),
    @NamedQuery(name="OCfuel.getSingleFuel",
                query="SELECT o FROM OCfuel o WHERE o.fuelType=:queryParam"),
    @NamedQuery(name="OCfuel.updatePrice",
                query="UPDATE OCfuel SET price = :queryparam WHERE id = :queryparam2")
})
@Entity
public class OCfuel extends OCsingleIdEntity implements Serializable {
    
    private String fuelType;
    private String description;
    private double price;

    public OCfuel(String liquid, String asuper, double d) {
        this.description = liquid;
        this.fuelType = asuper;
        this.price = d;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OCfuel() {
    }
    
    
        
}
