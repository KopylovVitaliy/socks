package com.demo;

import com.demo.entity.Socks;
import com.demo.repository.SocksRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InitialData {
    private final SocksRepository socksRepository;

    public Socks addSocks(){
        Socks socks = new Socks();
        socks.setColor("red");
        socks.setQuantity(10);
        socks.setCottonPart(50);
        return socksRepository.save(socks);
    }



}
