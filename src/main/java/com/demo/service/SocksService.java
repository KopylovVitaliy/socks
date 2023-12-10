package com.demo.service;

import com.demo.dto.ComeSocksDto;
import com.demo.dto.SocksDto;



public interface SocksService {
    SocksDto inComeSocks (ComeSocksDto comeSocksDto);
    SocksDto outComeSocks (ComeSocksDto comeSocksDto);

    SocksDto getSocks (String color,Operation operation, int cottonPart);

}
