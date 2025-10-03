package com.bolzanoprovince.tendering.mapper;


import com.bolzanoprovince.tendering.model.dto.requests.TenderRequestDto;
import com.bolzanoprovince.tendering.model.dto.responses.TenderResponseDto;
import com.bolzanoprovince.tendering.model.entity.Responsible;
import com.bolzanoprovince.tendering.model.entity.Tender;
import com.bolzanoprovince.tendering.model.entity.Vendor;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class TenderMapper {

    public static Tender toEntity (TenderRequestDto request, Responsible responsible, Set<Vendor> vendors, String protocolId, BigDecimal amount, Boolean isThreshold){
        Tender tender = new Tender();
        tender.setProtocolId(protocolId);
        tender.setDescription(request.getDescription());
        tender.setAmount(amount);
        tender.setIsThreshold(isThreshold);
        tender.setPublicVisibility(request.getPublicVisibility());
        tender.setDocAvailableUntilQualification(request.getDocAvailableUntilQualification());
        tender.setTenderType(request.getTenderType());
        tender.setResponsible(responsible);
        tender.setVendors(vendors);
        tender.setLots(request.getLots()
                .stream()
                .map(lot -> LotMapper.toEntity(lot, tender))
                .collect(Collectors.toList())
        );

        return tender;

    }

    public static TenderResponseDto toTenderResponseDto(Tender entity){

        TenderResponseDto response = new TenderResponseDto();
        response.setProtocolId(entity.getProtocolId());
        response.setDescription(entity.getDescription());
        response.setAmount(entity.getAmount());
        response.setPublicVisibility(entity.getPublicVisibility());
        response.setThreshold(entity.getIsThreshold());
        response.setDocAvailableUntilQualification(entity.getDocAvailableUntilQualification());
        response.setTenderType(entity.getTenderType());
        response.setResponsibleFullName(entity.getResponsible().getFirstName() + " " + entity.getResponsible().getLastName());
        response.setVendors(entity.getVendors()
                .stream()
                .map(Vendor::getName)
                .collect(Collectors.toSet()));
        response.setLots(entity.getLots()
                .stream()
                .map(LotMapper::toLotResponseDto)
                .collect(Collectors.toList()));

        return response;
    }
}
