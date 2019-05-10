package com.javacodegeeks.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.javacodegeeks.example.model.Shipment;

import reactor.bus.Event;
import reactor.bus.EventBus;

@Controller
public class EventController {
	@Autowired
	private EventBus eventBus;

	@GetMapping("/shipment/{param}")
	public void startShipment(@PathVariable Integer param) {
		for (int i = 0; i < param; i++) {
			Shipment shipment = new Shipment();
			shipment.setShipmentId(i);

			eventBus.notify("shipmentEventHandler", Event.wrap(shipment));

			System.out.println("Shipment " + i + ": task submitted successfully");
		}
	}
	
	@GetMapping("/delivery/{param}")
	public ResponseEntity<String> startDelivery(@PathVariable String param) {
		int p = Integer.valueOf(param);
		for (int i = 0; i < p; i++) {
			Shipment shipment = new Shipment();
			shipment.setShipmentId(i);

			eventBus.notify("deliveryEventHandler", Event.wrap(shipment));

			System.out.println("Delivery " + i + ": task submitted successfully");
		}
		return new ResponseEntity<String>("done...", HttpStatus.OK);
	}
}
