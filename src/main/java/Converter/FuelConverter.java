/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package Converter;

import de.oth.stelzer.swstelzer.entity.OCfuel;
import de.oth.stelzer.swstelzer.service.OrderService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tom
 */
@RequestScoped
@Named
public class FuelConverter implements Converter {

//    @PersistenceContext(unitName = "SWStelzer_pu")
//    private EntityManager entityManager;
    @Inject
    private OrderService orderService;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return "";
        }
        OCfuel fuel =  orderService.getFuelByType(value);
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
