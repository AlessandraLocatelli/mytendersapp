package com.bolzanoprovince.tendering.controller;

import com.bolzanoprovince.tendering.model.dto.responses.ResponsibleResponseDto;
import com.bolzanoprovince.tendering.service.interfaces.ResponsibleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/responsibles")
public class ResponsibleController {

    private final ResponsibleService responsibleService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponsibleResponseDto> getResponsibleById(@PathVariable Long id){

        ResponsibleResponseDto response = responsibleService.getResponsibleById(id);
        return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity<List<ResponsibleResponseDto>> getAllResponsibles(){

        List<ResponsibleResponseDto> response = responsibleService.getAllResponsibles();
        return ResponseEntity.ok(response);

    }



}
