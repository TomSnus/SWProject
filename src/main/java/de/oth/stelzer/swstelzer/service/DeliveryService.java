/*
 *  Softwareentwicklung Projekt
 *  Stelzer Thomas Matrikelnummer: 3001545
 *  Oil Company
 */
package de.oth.stelzer.swstelzer.service;

import de.oth.stelzer.swstelzer.delivery.Address;
import de.oth.stelzer.swstelzer.delivery.Customer;
import de.oth.stelzer.swstelzer.delivery.DeliveryOrder;
import de.oth.stelzer.swstelzer.delivery.Product;
import de.oth.stelzer.swstelzer.entity.OCcustomer;
import de.oth.stelzer.swstelzer.iface.IDeliveryService;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.jws.WebService;
import javax.transaction.Transactional;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Tom
 */
@WebService(serviceName = "DeliveryService")
@RequestScoped
public class DeliveryService implements IDeliveryService, Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/im-lamport_8080/SWJosefIlg-0.1/OrderService.wsdl")
    private de.oth.stelzer.swstelzer.delivery.OrderServiceService service;

    @Transactional
    @Override
    public DeliveryOrder createDeliveryorder(OCcustomer customer, OrderDTO orderDTO) {

        DeliveryOrder result = null;
        try { // Call Web Service Operation
            de.oth.stelzer.swstelzer.delivery.OrderService port = service.getOrderServicePort();
            // TODO initialize WS operation arguments here
            DeliveryOrder deliveryOrder = new DeliveryOrder();
            Customer c = new Customer();
            c.setId(81l);
            deliveryOrder.setCustomer(c);
            Address a = new Address();
            a.setState(customer.getAddress().getState());
            a.setStreet(customer.getAddress().getStreet());
            a.setStreetNumber(customer.getAddress().getHouseNumber());
            a.setZip(customer.getAddress().getZip());
            deliveryOrder.setDeliveryAddress(a);
            Product p = new Product();
            p.setAmountLiter((float) orderDTO.getAmount());
            p.setFuelType(orderDTO.getFuelType());
            deliveryOrder.setProduct(p);
            result = port.orderTransport(deliveryOrder);

            System.out.println("Result = " + result);
        } catch (Exception ex) {
            System.out.println("Could not Create order " + ex.getMessage());
        }
        return result;

    }

}
