package com.bolzanoprovince.tendering.service.interfaces;



import com.bolzanoprovince.tendering.model.dto.requests.LotRequestDto;
import com.bolzanoprovince.tendering.model.dto.responses.LotResponseDto;

import java.util.List;

public interface LotService {
    LotResponseDto createLot(LotRequestDto request);
    LotResponseDto getLotById(String cig);
    List<LotResponseDto> getAllLots();
    void deleteLotById (String cig);
    LotResponseDto updateLot (String cig, LotRequestDto request);
}
