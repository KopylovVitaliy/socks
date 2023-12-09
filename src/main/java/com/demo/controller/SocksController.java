package com.demo.controller;

import com.demo.dto.ComeSocksDto;
import com.demo.dto.SocksDto;
import com.demo.entity.Socks;
import com.demo.repository.SocksRepository;
import com.demo.service.Operation;
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
    public SocksDto addSocks (@RequestBody ComeSocksDto comeSocksDto) {
        return socksService.inComeSocks(comeSocksDto);
    }

    @PostMapping("/socks/outcome")
    public SocksDto outSocks (@RequestBody ComeSocksDto comeSocksDto){
        return socksService.outComeSocks(comeSocksDto);
    }

    @GetMapping("/socks")
    public SocksDto get(@RequestParam String color,
                        @RequestParam Operation operation,
                        @RequestParam int cottonPart){
        return socksService.getSocks(color, operation, cottonPart);
    }
    @GetMapping("/1")
    public String gete(@RequestParam Operation operation){
        return operation.getOperationType();
    }

}
