package com.javacodegeeks.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.javacodegeeks.example.handler.DeliveryEventHandler;
import com.javacodegeeks.example.handler.ShipmentEventHandler;
import com.javacodegeeks.example.model.Shipment;

import reactor.bus.Event;
import reactor.bus.EventBus;
import static reactor.bus.selector.Selectors.$;


@SpringBootApplication
public class Application implements CommandLineRunner 
{
	private final Logger LOG = LoggerFactory.getLogger("Application");
	 
    private final EventBus eventBus;
    private final ShipmentEventHandler shipmentEventHandler;
    private final DeliveryEventHandler deliveryEventHandler;
 
    @Autowired
    public Application(EventBus eventBus, ShipmentEventHandler shipmentEventHandler,DeliveryEventHandler deliveryEventHandler) {
        this.eventBus = eventBus;
        this.shipmentEventHandler = shipmentEventHandler;
        this.deliveryEventHandler=deliveryEventHandler;
    }
 
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
 
    public void run(String... strings) throws Exception {
        eventBus.on($("shipmentEventHandler"), shipmentEventHandler);
        eventBus.on($("deliveryEventHandler"), deliveryEventHandler);
//        //Publish messages here
//        for (int i = 0; i < 10; i++) {
//            Shipment shipment = new Shipment();
//            shipment.setShipmentId(String.valueOf(i));
//            eventBus.notify("shipmentEventHandler", Event.wrap(shipment));
//            eventBus.notify("deliveryEventHandler", Event.wrap(shipment));
//            LOG.info("Published shipment number {}.", i);
//        }
    }
}
