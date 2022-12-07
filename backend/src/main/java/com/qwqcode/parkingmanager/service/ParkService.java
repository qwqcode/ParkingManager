package com.qwqcode.parkingmanager.service;

import com.qwqcode.parkingmanager.entity.Car;
import com.qwqcode.parkingmanager.entity.Park;
import com.qwqcode.parkingmanager.mapper.ParkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkService {
    @Autowired
    private ParkMapper parkMapper;

    /** 查找停车场 */
    public Park findParkByID(int id) {
        return parkMapper.findParkByID(id);
    }
}
