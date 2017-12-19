/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.service;

import de.oth.stelzer.swstelzer.entity.OCfuel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.enterprise.event.Event;
import javax.inject.Inject;


/**
 *
 * @author Tom
 */
@Singleton
public class FuelPriceService {
    
    @Inject
    OrderService oService;
    
    private Random rng = new Random();
    @Schedule(second="*", minute="*/10", hour="*", persistent=false)
    public void changeFuelPrice() {
        DecimalFormat df = new DecimalFormat("0.00");
        List<OCfuel> fuelList = new ArrayList<>(oService.getAllFuels());
        Double newPrice = ThreadLocalRandom.current().nextDouble(0.5,2.0); 
        OCfuel fuel = fuelList.get(rng.nextInt(fuelList.size()));
        fuel.setPrice((double) Math.round(newPrice * 100) / 100);
        oService.updateFuelPrice(fuel);
    }
}
