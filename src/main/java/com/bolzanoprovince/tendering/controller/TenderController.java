package com.bolzanoprovince.tendering.controller;

import com.bolzanoprovince.tendering.model.dto.requests.TenderRequestDto;
import com.bolzanoprovince.tendering.model.dto.responses.TenderResponseDto;
import com.bolzanoprovince.tendering.service.interfaces.TenderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenders")
@RequiredArgsConstructor
public class TenderController {

    //better to inject the interface implemented for testability, flexibility and loosely coupling
    private final TenderService tenderService;

    @PostMapping
    public ResponseEntity<TenderResponseDto> createTender(@Valid @RequestBody TenderRequestDto request){

        TenderResponseDto response = tenderService.createTender(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public ResponseEntity<List<TenderResponseDto>> getAllTenders (){

        List<TenderResponseDto> response = tenderService.getAllTenders();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenderResponseDto> getTenderById (@PathVariable String id){

         TenderResponseDto response = tenderService.getTenderById(id);
         return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TenderResponseDto> updateTender (@PathVariable String id, @Valid @RequestBody TenderRequestDto request){

        TenderResponseDto response = tenderService.updateTender(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTender (@PathVariable String id){

        tenderService.deleteTenderById(id);
        return ResponseEntity.noContent().build();
    }


}
