package com.demo.service;

import com.demo.dto.ComeSocksDto;
import com.demo.dto.SocksDto;
import com.demo.entity.Socks;
import com.demo.exceptions.NotCottonPart;
import com.demo.exceptions.NotFoundSocks;
import com.demo.exceptions.NotQuantityException;
import com.demo.repository.SocksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {
    private final SocksMapper socksMapper;
    private final SocksRepository socksRepository;


    @Override
    public SocksDto inComeSocks(ComeSocksDto comeSocksDto) {
        if (socksRepository.findByColorAndCotton(comeSocksDto.getColor().toLowerCase(), comeSocksDto.getCottonPart()).isPresent()) {
            Socks socks = socksRepository.findByColorAndCotton(comeSocksDto.getColor().toLowerCase(), comeSocksDto.getCottonPart()).orElseThrow();
            socks.setQuantity(socks.getQuantity() + comeSocksDto.getQuantity());
            socksRepository.save(socks);
            return socksMapper.mapSocksToDto(socks);
        } else {
            Socks socks = socksMapper.mapInComeDtoToEntity(comeSocksDto);
            socksRepository.save(socks);
            return socksMapper.mapSocksToDto(socks);
        }
    }

    @Override
    public SocksDto outComeSocks(ComeSocksDto comeSocksDto) {
        if (socksRepository.findByColorAndCotton(comeSocksDto.getColor().toLowerCase(), comeSocksDto.getCottonPart()).isEmpty()) {
            throw new NotFoundSocks(comeSocksDto.getColor());
        }
        Socks socks = socksRepository.findByColorAndCotton(comeSocksDto.getColor().toLowerCase(), comeSocksDto.getCottonPart()).get();
        if (socks.getQuantity() - comeSocksDto.getQuantity() < 0) {
            throw new NotQuantityException(socks.getQuantity());
        }
        socks.setQuantity(socks.getQuantity() - comeSocksDto.getQuantity());
        socksRepository.save(socks);

        return socksMapper.mapSocksToDto(socks);
    }

    @Override
    public SocksDto getSocks(String color, Operation operation, int cottonPart) {
        if(socksRepository.findByColor(color).isEmpty()){
            throw new NotFoundSocks(color);
        }
        if (operation.getOperationType().equals("=")) {
            Socks socks = socksRepository.findByEquals(color, cottonPart).orElseThrow(NotCottonPart::new);
            return socksMapper.mapSocksToDto(socks);
        } else if (operation.getOperationType().equals(">")) {
            Socks socks = socksRepository.findByMoreThen(color, cottonPart).orElseThrow(NotCottonPart::new);
            return socksMapper.mapSocksToDto(socks);
        } else if (operation.getOperationType().equals("<")) {
            Socks socks = socksRepository.findByLessThen(color, cottonPart).orElseThrow(NotCottonPart::new);
            return socksMapper.mapSocksToDto(socks);
        }
        throw new NotFoundSocks(color);
    }
}
