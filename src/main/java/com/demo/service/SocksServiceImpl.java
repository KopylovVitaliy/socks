package com.demo.service;

import com.demo.dto.InComeSocksDto;
import com.demo.dto.SocksDto;
import com.demo.entity.Socks;
import com.demo.repository.SocksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService{
    private final SocksMapper socksMapper;
    private final SocksRepository socksRepository;


    @Override
    public SocksDto inComeSocks(InComeSocksDto inComeSocksDto) {
        if(socksRepository.findByColorAndCotton(inComeSocksDto.getColor(), inComeSocksDto.getCottonPart()).isPresent()){
            Socks socks = socksRepository.findByColorAndCotton(inComeSocksDto.getColor(), inComeSocksDto.getCottonPart()).orElseThrow();
            socks.setQuantity(socks.getQuantity() + inComeSocksDto.getQuantity());
            socksRepository.save(socks);
            return socksMapper.mapSocksToDto(socks);
        } else {
        Socks socks = socksMapper.mapInComeDtoToEntity(inComeSocksDto);
        socksRepository.save(socks);
        return socksMapper.mapSocksToDto(socks);}
    }

    @Override
    public void outComeSocks(InComeSocksDto inComeSocksDto) {

    }

    @Override
    public SocksDto getSocks(String color, int cottonPart) {
        return null;
    }
}
