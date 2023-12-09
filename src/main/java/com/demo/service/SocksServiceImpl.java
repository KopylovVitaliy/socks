package com.demo.service;

import com.demo.dto.InComeSocksDto;
import com.demo.dto.SocksDto;
import com.demo.entity.Socks;
import com.demo.repository.SocksRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService{
    private final SocksMapper socksMapper;
    private final SocksRepository socksRepository;


    @Override
    public SocksDto inComeSocks(InComeSocksDto inComeSocksDto) {
        Socks socks = socksMapper.mapInComeDtoToEntity(inComeSocksDto);
        socksRepository.save(socks);
        return socksMapper.mapSocksToDto(socks);
    }

    @Override
    public void outComeSocks(InComeSocksDto inComeSocksDto) {

    }

    @Override
    public SocksDto getSocks(String color, int cottonPart) {
        return null;
    }
}
