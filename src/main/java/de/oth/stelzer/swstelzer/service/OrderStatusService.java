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
import de.oth.stelzer.swstelzer.delivery.Status;
import de.oth.stelzer.swstelzer.entity.OCstatus;
import java.util.stream.Collectors;

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

    /**
     * Updating Status of not finished orders every 10 min
     */
    @Schedule(second = "*", minute = "*/10", hour = "*", persistent = false)
    public void updateOrderStatus() {
        List<OCorder> orderList = new ArrayList<>(oService.getAllOrders());
        orderList = orderList
                .stream()
                .filter(p -> !p.getStatus().equals(OCstatus.FINISHED))
                .collect(Collectors.toList());
        try { // Call Web Service Operation
            de.oth.stelzer.swstelzer.delivery.OrderService port = service.getOrderServicePort();
            de.oth.stelzer.swstelzer.delivery.DeliveryOrder dOrder = new de.oth.stelzer.swstelzer.delivery.DeliveryOrder();
            for(OCorder order : orderList){
                OCstatus newStatus = OCstatus.SHIPPED;
                dOrder.setId(order.getTranspordId());
                de.oth.stelzer.swstelzer.delivery.Status result = port.getDeliveryStatus(dOrder);
                order.setStatusDescription(result.name());
                if(result.equals(Status.FINISHED)) {
                    newStatus = OCstatus.FINISHED; 
                }
                oService.updateStatus(order, newStatus, result.name());
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error: Could not receive Order Status", ex);
        }

    }
}