package com.javacodegeeks.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.javacodegeeks.example.model.Shipment;

@Service
public class DeliveryServiceImpl implements DeliveryService{

	private final Logger LOG = LoggerFactory.getLogger("DeliveryService");
	public void deliveryLocationUpdate(Shipment shipment) throws InterruptedException {
		LOG.info("Delivery data: {}", shipment.getShipmentId());
        Thread.sleep(3000);
        LOG.info("Delivery with ID: {} reached at javacodegeeks!!!", shipment.getShipmentId());
		
	}

}
