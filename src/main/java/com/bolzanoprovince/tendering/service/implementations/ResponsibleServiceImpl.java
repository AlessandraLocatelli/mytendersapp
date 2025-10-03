package com.bolzanoprovince.tendering.service.implementations;

import com.bolzanoprovince.tendering.mapper.ResponsibleMapper;
import com.bolzanoprovince.tendering.model.dto.responses.ResponsibleResponseDto;
import com.bolzanoprovince.tendering.model.entity.Responsible;
import com.bolzanoprovince.tendering.repository.ResponsibleRepository;
import com.bolzanoprovince.tendering.service.interfaces.ResponsibleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class ResponsibleServiceImpl implements ResponsibleService {

    private final ResponsibleRepository responsibleRepository;

    @Override
    public ResponsibleResponseDto getResponsibleById(Long id) {

        Responsible responsible = responsibleRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException("Responsible with id "+id+" not found."));

        return ResponsibleMapper.toResponsibleResponseDto(responsible);

    }

    @Override
    public List<ResponsibleResponseDto> getAllResponsibles() {

        List<Responsible> responsibles = responsibleRepository.findAll();

        return responsibles
                .stream()
                .map(responsible -> new ResponsibleResponseDto(responsible.getLastName() + " " + responsible.getLastName()))
                .collect(Collectors.toList());

    }
}
