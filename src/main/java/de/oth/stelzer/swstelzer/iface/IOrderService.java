/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.iface;

import de.oth.stelzer.swstelzer.entity.OCfuel;
import de.oth.stelzer.swstelzer.entity.OCorder;
import de.oth.stelzer.swstelzer.entity.OCstatus;
import de.oth.stelzer.swstelzer.service.FuelDTO;
import de.oth.stelzer.swstelzer.service.OrderDTO;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Tom
 */
public interface IOrderService {
    
    public Collection<OCfuel> getAllFuels();
    
    public OCfuel getFuelByType(String ft);
    
    public void insertFuel(OCfuel fuel);
    
    public void updateFuelPrice(OCfuel fuel);
    
    public OCfuel getFuelByDTO(FuelDTO fdto);
    
    public OCorder createOrder(OrderDTO orderDTO);
    
    public Collection<OCorder> getAllOrders();
    
    public OCorder getStatusDescription(long transportId);
    
    public void updateStatus(OCorder order, OCstatus status, String statusDesc);
    
    public void removeFuel(OCfuel item);
    
    
}
