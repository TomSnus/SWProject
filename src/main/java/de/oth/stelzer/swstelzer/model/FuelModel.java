/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.model;

import de.oth.stelzer.swstelzer.entity.OCfuel;
import de.oth.stelzer.swstelzer.service.OrderService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Tom
 */
@Named(value="fuelModel")
@SessionScoped
public class FuelModel implements Serializable{

    private String fuelType;
    private String description;
    private Double price;
    private String selectedItem;
    private Double newPrice;
    
    private Map<OCfuel, Boolean> checked = new HashMap<>();
    private List<SelectItem> fuelList;
    @Inject
    private OrderService orderService;

    public Collection<OCfuel> allFuels() {
        return this.orderService.getAllFuels();
    }

    public String removeFuel() {
        for (Map.Entry<OCfuel, Boolean> entry : checked.entrySet()) {
            if (entry.getValue()) {
                orderService.removeFuel(entry.getKey());
            }
        }

        //clean checked list
        checked.clear();

        return "fuel_index.xhtml";
    }
    
    public void updatePrice(){
        
    }
    
    public String verifyFuel() {
        OCfuel newFuel = new OCfuel();
        newFuel.setDescription(this.description);
        newFuel.setFuelType(this.fuelType);
        newFuel.setPrice(this.price);

        if (!newFuel.getFuelType().equals("")) {
            orderService.insertFuel(newFuel);
            cleanAttributs();
        }

        return "fuel_index.xhtml";
    }

    private void cleanAttributs() {
        this.description = "";
        this.price = null;
        this.fuelType = "";
    }
    
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }

        try {
            return submittedValue;
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid User ID", submittedValue)), e);
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Map<OCfuel, Boolean> getChecked() {
        return checked;
    }

    public void setChecked(Map<OCfuel, Boolean> checked) {
        this.checked = checked;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<SelectItem> getFuelList() {
        return fuelList;
    }

    public void setFuelList(List<SelectItem> fuelList) {
        this.fuelList = fuelList;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }
    
    
   
    
    
    
}
