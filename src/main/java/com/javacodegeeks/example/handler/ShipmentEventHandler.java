package com.javacodegeeks.example.handler;


import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javacodegeeks.example.model.Shipment;
import com.javacodegeeks.example.service.ShipmentService;

import reactor.bus.Event;
import reactor.fn.Consumer;

@Service
public class ShipmentEventHandler implements Consumer<Event<Shipment>>{
	private final ShipmentService shipmentService;
	private CountDownLatch latch;
    @Autowired
    public ShipmentEventHandler(ShipmentService shipmentService,CountDownLatch latch) {
        this.shipmentService = shipmentService;
        this.latch=latch;
    }
 
    public void accept(Event<Shipment> shipmentEvent) {
        Shipment shipment = shipmentEvent.getData();
        try {
            shipmentService.shipmentLocationUpdate(shipment);
        } catch (InterruptedException e) {
            
        }
        latch.countDown();
//        return shipmentId;
    }
}
