package com.bolzanoprovince.tendering.service.interfaces;

import com.bolzanoprovince.tendering.model.dto.requests.TenderRequestDto;
import com.bolzanoprovince.tendering.model.dto.responses.TenderResponseDto;

import java.util.List;


public interface TenderService {
    TenderResponseDto createTender(TenderRequestDto request);
    TenderResponseDto getTenderById(String protocolId);
    List<TenderResponseDto> getAllTenders();
    void deleteTenderById (String protocolId);
    TenderResponseDto updateTender (String protocolId, TenderRequestDto request);
}
