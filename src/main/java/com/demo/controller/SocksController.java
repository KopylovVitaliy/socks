package com.demo.controller;

import com.demo.dto.InComeSocksDto;
import com.demo.dto.SocksDto;
import com.demo.entity.Socks;
import com.demo.repository.SocksRepository;
import com.demo.service.SocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SocksController {
    private final SocksService socksService;
    private final SocksRepository socksRepository;

    @PostMapping("/socks/income")
    public SocksDto addSocks (@RequestBody InComeSocksDto inComeSocksDto) {
        return socksService.inComeSocks(inComeSocksDto);
    }

    @GetMapping
    public Socks get(){
        return socksRepository.findByColorAndCotton("red", 20).get();
    }

}
