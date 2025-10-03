package com.bolzanoprovince.tendering.service.interfaces;

import com.bolzanoprovince.tendering.model.dto.responses.ResponsibleResponseDto;

import java.util.List;

public interface ResponsibleService {

    ResponsibleResponseDto getResponsibleById(Long id);
    List<ResponsibleResponseDto> getAllResponsibles();

}
