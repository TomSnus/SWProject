/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.service;

import de.oth.stelzer.swstelzer.entity.OCfuel;
import de.oth.stelzer.swstelzer.iface.IFuelPriceService;
import de.oth.stelzer.swstelzer.resources.qualifier.OptionOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import org.apache.logging.log4j.Logger;


/**
 * Sheduled Method for price changes
 * @author Tom
 */
@Singleton
public class FuelPriceService implements IFuelPriceService{
    
    @Inject
    OrderService oService;
    
    @Inject
    @OptionOrder
    private Logger orderLogger;
     
    private Random rng = new Random();

    /**
     * Chaning Price every 10 min to random value between 0.5 and 2.0
     */
    @Schedule(minute="*/10", hour="*", persistent=false)
    @Override
    public void changeFuelPrice() {
        List<OCfuel> fuelList = new ArrayList<>(oService.getAllFuels());
        Double newPrice = ThreadLocalRandom.current().nextDouble(0.5,2.0); 
        OCfuel fuel = fuelList.get(rng.nextInt(fuelList.size()));
        fuel.setPrice((double) Math.round(newPrice * 100) / 100);
        oService.updateFuelPrice(fuel);
        orderLogger.info("Fuel price updated. fuel id: " + fuel.getId());
    }
}
