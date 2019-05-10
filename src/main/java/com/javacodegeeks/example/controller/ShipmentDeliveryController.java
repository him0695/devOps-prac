package com.javacodegeeks.example.controller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javacodegeeks.example.model.Shipment;

import reactor.bus.Event;
import reactor.bus.EventBus;

@Controller
public class ShipmentDeliveryController {
	@Autowired
	private EventBus eventBus;

	@GetMapping("/shipment/delivery/{id1}/{id2}")
	public void startShipment(@PathVariable Integer id1, @PathVariable Integer id2) {

		CountDownLatch latch = new CountDownLatch(2);
		
		Shipment shipment = new Shipment();
		shipment.setShipmentId(1);

		if (id1 == 1) {
			eventBus.notify("shipmentEventHandler", Event.wrap(shipment,latch));
		}
		if (id2 == 1)
			eventBus.notify("deliveryEventHandler", Event.wrap(shipment,latch));

		try {
			latch.await(5, TimeUnit.SECONDS);
			if(latch.getCount()==0) System.out.println("both events done");
			else if(latch.getCount()==1)  System.out.println("one events done");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//			System.out.println("Shipment " + 1 + ": task submitted successfully");

	}
}
