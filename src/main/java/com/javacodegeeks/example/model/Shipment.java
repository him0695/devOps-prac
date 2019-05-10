package com.javacodegeeks.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Shipment {
	private int shipmentId;
    private String name;
    private String currentLocation;
    private String deliveryAddress;
    private String status;
	
    
    
}
