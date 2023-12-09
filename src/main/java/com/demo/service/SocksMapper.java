package com.demo.service;

import com.demo.dto.ComeSocksDto;
import com.demo.dto.SocksDto;
import com.demo.entity.Socks;
import org.springframework.stereotype.Component;

@Component
public class SocksMapper {

    public Socks mapDtoToEntity(SocksDto socksDto){
        Socks socks = new Socks();
        socks.setId(socksDto.getId());
        socks.setColor(socksDto.getColor());
        socks.setCottonPart(socksDto.getCottonPart());
        socks.setQuantity(socksDto.getQuantity());
        return socks;
    }
    public Socks mapInComeDtoToEntity(ComeSocksDto socksDto){
        Socks socks = new Socks();
        socks.setColor(socksDto.getColor());
        socks.setCottonPart(socksDto.getCottonPart());
        socks.setQuantity(socksDto.getQuantity());
        return socks;
    }

    public SocksDto mapSocksToDto(Socks socks){
        SocksDto socksDto = new SocksDto();
        socksDto.setId(socks.getId());
        socksDto.setColor(socks.getColor());
        socksDto.setCottonPart(socks.getCottonPart());
        socksDto.setQuantity(socks.getQuantity());
        return socksDto;
    }

    public ComeSocksDto mapSocksToInComeDto(Socks socks){
        ComeSocksDto comeSocksDto = new ComeSocksDto();
        comeSocksDto.setColor(socks.getColor());
        comeSocksDto.setQuantity(socks.getQuantity());
        comeSocksDto.setCottonPart(socks.getCottonPart());
        return comeSocksDto;
    }

}
