/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.service;

import de.oth.stelzer.swstelzer.entity.OCfuel;
import de.oth.stelzer.swstelzer.entity.OCorder;
import de.oth.stelzer.swstelzer.repository.FuelRepository;
import de.oth.stelzer.swstelzer.repository.OrderRepository;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.jws.WebService;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author Tom
 */

@WebService(serviceName="OrderService")
@RequestScoped
public class OrderService {

    
    private final OrderRepository orderRepo = new OrderRepository();
    private final FuelRepository fuelRepo = new FuelRepository();
    @Transactional
    public OCorder order(OCorder order){
       orderRepo.add(order);
       return order;
    }

    public void remove(OCorder item) {
        orderRepo.remove(item);
    }
    
    @Transactional
    public Double getPrice(String ft){

        return fuelRepo.getPrice(ft);
    }
    
     @Transactional
    public List<OCfuel> getAllPrices(){

        return fuelRepo.getAllPrices();
    }
    
    @Transactional
    public void insertFuel(OCfuel fuel) {
        fuelRepo.insertFuel(fuel);
    }
}
