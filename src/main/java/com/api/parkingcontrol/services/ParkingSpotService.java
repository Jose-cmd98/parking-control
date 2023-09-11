package com.api.parkingcontrol.services;

//é uma camada intermediaria entre a controler e o repository

import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingSpotService {

    @Autowired
    public ParkingSpotRepository parkingSportService;
}
