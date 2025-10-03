package com.bolzanoprovince.tendering.controller;

import com.bolzanoprovince.tendering.model.dto.requests.LotRequestDto;
import com.bolzanoprovince.tendering.model.dto.responses.LotResponseDto;
import com.bolzanoprovince.tendering.model.entity.Lot;
import com.bolzanoprovince.tendering.service.interfaces.LotService;
import com.bolzanoprovince.tendering.service.interfaces.TenderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lots")
public class LotController {

    private final LotService lotService;

    @GetMapping
    public ResponseEntity<List<LotResponseDto>> getAllLots(){

        List<LotResponseDto> response = lotService.getAllLots();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LotResponseDto> getLotById(@PathVariable String id){

        LotResponseDto response = lotService.getLotById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<LotResponseDto> createLot(@Valid @RequestBody LotRequestDto request){

        LotResponseDto response = lotService.createLot(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LotResponseDto> updateLot(@Valid @RequestBody LotRequestDto request, String id){

        LotResponseDto response = lotService.updateLot(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLot( String id){

        lotService.deleteLotById(id);
        return ResponseEntity.noContent().build();
    }







}
