package com.bolzanoprovince.tendering.service.implementations;


import com.bolzanoprovince.tendering.mapper.VendorMapper;
import com.bolzanoprovince.tendering.model.dto.responses.VendorResponseDto;
import com.bolzanoprovince.tendering.model.entity.Vendor;
import com.bolzanoprovince.tendering.repository.VendorRepository;
import com.bolzanoprovince.tendering.service.interfaces.VendorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor


@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    @Override
    public VendorResponseDto getVendorById(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vendor with id " + id + " not found."));


        return VendorMapper.toVendorResponseDto(vendor);

    }


    @Override
    public List<VendorResponseDto> getAllVendors() {
        List<Vendor> vendors = vendorRepository.findAll();
        return vendors.stream()
                .map(vendor -> new VendorResponseDto(vendor.getName()))
                .collect(Collectors.toList());
    }

}
