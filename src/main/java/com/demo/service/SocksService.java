package com.demo.service;

import com.demo.dto.InComeSocksDto;
import com.demo.dto.SocksDto;



public interface SocksService {
    SocksDto inComeSocks (InComeSocksDto inComeSocksDto);
    void outComeSocks (InComeSocksDto inComeSocksDto);

    SocksDto getSocks (String color, int cottonPart);

}
