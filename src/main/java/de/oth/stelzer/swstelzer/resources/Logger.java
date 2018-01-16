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
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.apache.log4j.LogManager;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Tom
 */
@ApplicationScoped
public class Logger {

    @ApplicationScoped
    @OptionCustomer
    public org.apache.log4j.Logger customerLogger() {
        return LogManager.getLogger(CRMService.class);
    }

    @Produces
    @ApplicationScoped
    @OptionOrder
    public org.apache.log4j.Logger orderLogger() {
        return LogManager.getLogger(OrderService.class);
    }


}
