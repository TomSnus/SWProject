/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.iface;

import de.oth.stelzer.swstelzer.delivery.DeliveryOrder;
import de.oth.stelzer.swstelzer.entity.OCcustomer;
import de.oth.stelzer.swstelzer.entity.OrderDTO;

/**
 *
 * @author Tom
 */
public interface IDeliveryService {
    DeliveryOrder createDeliveryorder(OCcustomer customer, OrderDTO orderDTO);
}
