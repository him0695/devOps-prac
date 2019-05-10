package com.javacodegeeks.example.service;

import com.javacodegeeks.example.model.Shipment;

public interface ShipmentService {
	void shipmentLocationUpdate(Shipment shipment) throws InterruptedException;
}
