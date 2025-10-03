package com.bolzanoprovince.tendering.controller;

import com.bolzanoprovince.tendering.model.dto.responses.VendorResponseDto;
import com.bolzanoprovince.tendering.service.interfaces.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/vendors")
public class VendorController {


    private final VendorService vendorService;

    @GetMapping("/{id}")
    public ResponseEntity<VendorResponseDto> getVendorById(@PathVariable Long id){

        VendorResponseDto response = vendorService.getVendorById(id);
        return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity<List<VendorResponseDto>> getAllVendors(){

        List<VendorResponseDto> response = vendorService.getAllVendors();
        return ResponseEntity.ok(response);

    }








}
