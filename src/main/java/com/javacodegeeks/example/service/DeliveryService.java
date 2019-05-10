package com.javacodegeeks.example.service;

import com.javacodegeeks.example.model.Shipment;

public interface DeliveryService {

	void deliveryLocationUpdate(Shipment shipment) throws InterruptedException;

}
