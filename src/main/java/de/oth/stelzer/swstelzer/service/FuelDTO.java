/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.service;

/**
 *
 * @author Tom
 */
public class FuelDTO {
    
    String fueltype;

    public FuelDTO() {
    }

    public FuelDTO(String fueltype) {
        this.fueltype = fueltype;
    }

    public String getFueltype() {
        return fueltype;
    }

    public void setFueltype(String fueltype) {
        this.fueltype = fueltype;
    }
    
    
    
}
