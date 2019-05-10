package com.javacodegeeks.example.handler;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javacodegeeks.example.model.Shipment;
import com.javacodegeeks.example.service.DeliveryService;
import com.javacodegeeks.example.service.ShipmentService;

import reactor.bus.Event;
import reactor.fn.Consumer;

@Service
public class DeliveryEventHandler implements Consumer<Event<Shipment>> {
//	@Autowired
//	private DeliveryService deliveryService;
 
	
	private DeliveryService deliveryService;
	private CountDownLatch latch;
    @Autowired
    public DeliveryEventHandler(DeliveryService deliveryService,CountDownLatch latch) {
        this.deliveryService = deliveryService;
        this.latch=latch;
    }
	
    public void accept(Event<Shipment> shipmentEvent) {
        Shipment shipment = shipmentEvent.getData();
        try {
        	deliveryService.deliveryLocationUpdate(shipment);
        } catch (InterruptedException e) {
            
        }
        latch.countDown();
    }
}
