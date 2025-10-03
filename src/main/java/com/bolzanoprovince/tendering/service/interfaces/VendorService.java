package com.bolzanoprovince.tendering.service.interfaces;

import com.bolzanoprovince.tendering.model.dto.responses.VendorResponseDto;

import java.util.List;

public interface VendorService {

    VendorResponseDto getVendorById(Long id);
    List<VendorResponseDto> getAllVendors();
}
