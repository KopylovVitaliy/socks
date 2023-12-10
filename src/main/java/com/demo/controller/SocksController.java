package com.demo.controller;

import com.demo.dto.ComeSocksDto;
import com.demo.dto.SocksDto;
import com.demo.service.Operation;
import com.demo.service.SocksService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SocksController {
    private final SocksService socksService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input / bad request", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("/socks/income")
    public SocksDto addSocks (@RequestBody ComeSocksDto comeSocksDto) {
        return socksService.inComeSocks(comeSocksDto);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input / bad request", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping("/socks/outcome")
    public SocksDto outSocks (@RequestBody ComeSocksDto comeSocksDto){
        return socksService.outComeSocks(comeSocksDto);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input / bad request", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/socks")
    public SocksDto get(@RequestParam String color,
                        @RequestParam Operation operation,
                        @RequestParam int cottonPart){
        return socksService.getSocks(color, operation, cottonPart);
    }

}
