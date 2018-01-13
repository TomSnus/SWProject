/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.converter;

import de.oth.stelzer.swstelzer.entity.OCfuel;
import de.oth.stelzer.swstelzer.service.OrderService;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Tom
 */
@RequestScoped
@Named
public class FuelConverter implements Converter {

    @Inject
    private OrderService orderService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return "";
        }
        OCfuel fuel = orderService.getFuelByType(value);
        if (fuel == null) {
            return "";
        }
        return fuel;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        if (!value.getClass().equals(OCfuel.class)) {
            return null;
        }
        return ((OCfuel) value).getFuelType();
    }

}
