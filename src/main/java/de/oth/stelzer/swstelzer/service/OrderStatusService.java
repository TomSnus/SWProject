/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.service;

import de.oth.stelzer.swstelzer.iface.IOrderStatusService;
import de.oth.stelzer.swstelzer.entity.OCorder;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.xml.ws.WebServiceRef;
import de.oth.stelzer.swstelzer.delivery.OrderServiceService;
import de.oth.stelzer.swstelzer.delivery.Status;
import de.oth.stelzer.swstelzer.entity.OCstatus;
import de.oth.stelzer.swstelzer.iface.IOrderService;
import de.oth.stelzer.swstelzer.resources.Environment;
import de.oth.stelzer.swstelzer.resources.qualifier.OptionOrder;
import java.util.stream.Collectors;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Tom
 */
@Singleton
public class OrderStatusService implements IOrderStatusService{

    Environment environment = TestDeliveryService.environment;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/im-lamport_8080/SWJosefIlg-0.1/OrderService.wsdl")
    private OrderServiceService service;
    @Inject
    IOrderService oService;
    
    @Inject
    @OptionOrder
    private Logger orderLogger;
     
    @Inject
    TestDeliveryService testDelService;

    /**
     * Updating Status of not finished orders every minute
     * 
     * If Partner set Status of the Deliveryorder to "FINISHED"
     * Order.status is set to FINISHED.
     * 
     * Standard Status for shipped orders = SHIPPED
     */
    @Schedule(second="*/60", minute = "*", hour="*", persistent = false)
    @Override
    public void updateOrderStatus() {
        List<OCorder> orderList = new ArrayList<>(oService.getAllOrders());
        orderList = orderList
                .stream()
                .filter(p -> !p.getStatus().equals(OCstatus.FINISHED))
                .collect(Collectors.toList());
        try { 
            de.oth.stelzer.swstelzer.delivery.OrderService port = service.getOrderServicePort();
            de.oth.stelzer.swstelzer.delivery.DeliveryOrder dOrder = new de.oth.stelzer.swstelzer.delivery.DeliveryOrder();
            for (OCorder order : orderList) {
                OCstatus newStatus = OCstatus.SHIPPED;
                dOrder.setId(order.getTransportId());
                de.oth.stelzer.swstelzer.delivery.Status result= null;
                if (environment.equals(Environment.PROD)) {
                    result = port.getDeliveryStatus(dOrder);
                } else if (environment.equals(Environment.TEST)) {
                    result = testDelService.getDeliveryStatus(dOrder);
                }
                order.setStatusDescription(result.value());
                if (result.equals(Status.FINISHED)) {
                    newStatus = OCstatus.FINISHED;
                }
                oService.updateStatus(order, newStatus, result.name());
                orderLogger.info("Status updated. order id: " + order.getId() + " old status: "+ order.getStatus() + " new status: " + result.name());
            }
        } catch (Exception ex) {
            orderLogger.error("Could not receive Status of order. Exception: " + ex.getMessage());
        }

    }
}
