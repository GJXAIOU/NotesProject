package com.gjxaiou.service.impl;

import com.gjxaiou.mapper.AirportMapper;
import com.gjxaiou.pojo.Airport;
import com.gjxaiou.service.AirportService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author GJXAIOU
 * @create 2019-09-23-18:32
 */
public class AirportServiceImpl implements AirportService {
   @Getter
   @Setter
    private AirportMapper airportMapper;

    @Override
    public List<Airport> show() {
        return airportMapper.selAll();
    }
}
