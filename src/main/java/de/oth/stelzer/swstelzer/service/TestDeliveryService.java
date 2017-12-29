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
import de.oth.stelzer.swstelzer.delivery.Status;
import de.oth.stelzer.swstelzer.entity.OCcustomer;
import de.oth.stelzer.swstelzer.entity.OrderDTO;
import de.oth.stelzer.swstelzer.iface.IDeliveryService;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Tom
 */
@WebService(serviceName="TestDeliveryService")
@RequestScoped
public class TestDeliveryService implements IDeliveryService, Serializable {

    @Override
    public DeliveryOrder createDeliveryorder(OCcustomer customer, OrderDTO orderDTO) {
        DeliveryOrder deliveryOrder = null;
        try {
            GregorianCalendar gc = new GregorianCalendar();

            deliveryOrder = new DeliveryOrder();
            //Customer
            Customer c = new Customer();
            c.setId(81l);
            deliveryOrder.setCustomer(c);
            //Address
            Address a = new Address();
            a.setState(customer.getAddress().getState());
            a.setStreet(customer.getAddress().getStreet());
            a.setStreetNumber(customer.getAddress().getHouseNumber());
            a.setZip(customer.getAddress().getZip());
            deliveryOrder.setDeliveryAddress(a);
            //Product
            Product p = new Product();
            p.setAmountLiter((float) orderDTO.getAmount());
            p.setFuelType(orderDTO.getFuelType());
            deliveryOrder.setProduct(p);
            //Status
            deliveryOrder.setStatus(Status.DELIVERY);
            //Date
            deliveryOrder.setOrderDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            deliveryOrder.setDeliveryDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            deliveryOrder.setCollectionDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            //Id
            deliveryOrder.setId(1337);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(TestDeliveryService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return deliveryOrder;
        }
    }

}
