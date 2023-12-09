package com.demo.controller;

import com.demo.dto.InComeSocksDto;
import com.demo.dto.SocksDto;
import com.demo.service.SocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SocksController {
    private final SocksService socksService;

    @PostMapping("/socks/income")
    public SocksDto addSocks (@RequestPart InComeSocksDto inComeSocksDto) {
        return socksService.inComeSocks(inComeSocksDto);
    }





}
