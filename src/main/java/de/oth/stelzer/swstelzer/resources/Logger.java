/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.resources;

import de.oth.stelzer.swstelzer.resources.qualifier.OptionCustomer;
import de.oth.stelzer.swstelzer.resources.qualifier.OptionOrder;
import de.oth.stelzer.swstelzer.service.CRMService;
import de.oth.stelzer.swstelzer.service.OrderService;
import javax.enterprise.inject.Produces;
import javax.enterprise.context.ApplicationScoped;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Tom
 */
@ApplicationScoped
public class Logger {
    
    @Produces
    @ApplicationScoped
    @OptionCustomer
    public org.apache.logging.log4j.Logger customerLogger() {
        return LogManager.getLogger(CRMService.class);
    }

    @Produces
    @ApplicationScoped
    @OptionOrder
    public org.apache.logging.log4j.Logger orderLogger() {
        return LogManager.getLogger(OrderService.class);
    }


}
