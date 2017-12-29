/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.service;

import de.oth.stelzer.swstelzer.entity.OCfuel;
import de.oth.stelzer.swstelzer.entity.OCorder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.xml.ws.WebServiceRef;
import de.oth.stelzer.swstelzer.delivery.OrderServiceService;

/**
 *
 * @author Tom
 */
@Singleton
public class OrderStatusService {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/im-lamport_8080/SWJosefIlg-0.1/OrderService.wsdl")
    private OrderServiceService service;
    @Inject
    OrderService oService;

    private Random rng = new Random();

    /**
     * Updating Status of not finished orders every 10 min
     */
    @Schedule(second = "*", minute = "*/10", hour = "*", persistent = false)
    public void updateOrderStatus() {
        List<OCorder> orderList = new ArrayList<>(oService.getAllOrders());

        try { // Call Web Service Operation
            de.oth.stelzer.swstelzer.delivery.OrderService port = service.getOrderServicePort();
            // TODO initialize WS operation arguments here
            de.oth.stelzer.swstelzer.delivery.DeliveryOrder dOrder = new de.oth.stelzer.swstelzer.delivery.DeliveryOrder();
            
            de.oth.stelzer.swstelzer.delivery.Status result = port.getDeliveryStatus(dOrder);
            System.out.println("Result = " + result);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

    }
}
