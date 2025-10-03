package com.bolzanoprovince.tendering.service.implementations;

import com.bolzanoprovince.tendering.mapper.TenderMapper;
import com.bolzanoprovince.tendering.model.dto.requests.LotRequestDto;
import com.bolzanoprovince.tendering.model.dto.requests.TenderRequestDto;
import com.bolzanoprovince.tendering.model.dto.responses.TenderResponseDto;
import com.bolzanoprovince.tendering.model.entity.Lot;
import com.bolzanoprovince.tendering.model.entity.Responsible;
import com.bolzanoprovince.tendering.model.entity.Tender;
import com.bolzanoprovince.tendering.model.entity.Vendor;
import com.bolzanoprovince.tendering.model.enums.TenderType;
import com.bolzanoprovince.tendering.repository.LotRepository;
import com.bolzanoprovince.tendering.repository.ResponsibleRepository;
import com.bolzanoprovince.tendering.repository.TenderRepository;
import com.bolzanoprovince.tendering.repository.VendorRepository;
import com.bolzanoprovince.tendering.service.interfaces.TenderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
@Transactional //there are methods that modify the db (ex. delete)
public class TenderServiceImpl implements TenderService {

    private final ResponsibleRepository responsibleRepository;
    private final VendorRepository vendorRepository;
    private final LotRepository lotRepository;
    private final TenderRepository tenderRepository;


    @Override
    public TenderResponseDto createTender(TenderRequestDto request) {
        checkValidations(request);

        Set<Vendor> vendors = prepareVendors(request);
        Responsible responsible = prepareResponsible(request);
        String protocolId = generateProtocolId();
        BigDecimal amount = calculateAmount(request);
        Boolean isThreshold = Boolean.TRUE.equals(isThreshold(request));
        Tender savedTender = TenderMapper.toEntity(request, responsible, vendors, protocolId, amount, isThreshold);

        savedTender = tenderRepository.save(savedTender);

        return TenderMapper.toTenderResponseDto(savedTender);
    }



    @Override
    public TenderResponseDto getTenderById(String protocolId) {

        Tender tender = tenderRepository.findById(protocolId).orElseThrow(() -> new EntityNotFoundException("id "+protocolId+ " not found."));

        return TenderMapper.toTenderResponseDto(tender);
    }

    @Override
    public List<TenderResponseDto> getAllTenders() {

        return tenderRepository.findAll()
                .stream()
                .map(TenderMapper::toTenderResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTenderById(String protocolId) {

        if(!tenderRepository.existsById(protocolId)){

            throw new EntityNotFoundException("id "+protocolId+" not found.") ;
        }

       tenderRepository.deleteById(protocolId);
    }

    @Override
    public TenderResponseDto updateTender(String protocolId, TenderRequestDto request) {

        if(!tenderRepository.existsById(protocolId)){

            throw new EntityNotFoundException("id "+protocolId+" not found.") ;
        }

        checkValidations(request);

        Set<Vendor> vendors = prepareVendors(request);
        Responsible responsible = prepareResponsible(request);
        BigDecimal amount = calculateAmount(request);
        Boolean isThreshold = Boolean.TRUE.equals(isThreshold(request));
        Tender savedTender = TenderMapper.toEntity(request, responsible, vendors, protocolId, amount, isThreshold);

        savedTender = tenderRepository.save(savedTender);

        return TenderMapper.toTenderResponseDto(savedTender);



    }


    private void checkValidations(TenderRequestDto request) {

        if (request.getTenderType().requiresVendors()) {
            if (request.getVendorsId() == null || request.getVendorsId().isEmpty()) {
                throw new IllegalArgumentException("At least one vendor ID must be provided for this tender type.");
            }
        } else {
            if (request.getVendorsId() != null && !request.getVendorsId().isEmpty()) {
                throw new IllegalArgumentException("This tender type must not have vendors.");
            }
        }

        if(TenderType.RESTRICTED_PROCEDURE.equals(request.getTenderType()) && request.getPublicVisibility()){

            throw new IllegalArgumentException("Restricted procedures are only private.");

        }

    }

    private Set<Vendor> prepareVendors(TenderRequestDto request) {

        Set<Long> vendorIds = request.getVendorsId();

        return  vendorIds.stream()
                .map(id -> vendorRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Vendor with ID " + id + " not found")))
                .collect(Collectors.toSet());
    }

    private Responsible prepareResponsible (TenderRequestDto request) {

        return responsibleRepository.findById(request.getResponsibleId()).orElseThrow(() -> new EntityNotFoundException("Responsible with ID " + request.getResponsibleId() + " not found"));
    }



    private BigDecimal calculateAmount(TenderRequestDto request) {
        List<LotRequestDto> lots = request.getLots();

        if (lots == null || lots.isEmpty()) {
            throw new IllegalArgumentException("At least one lot must be provided.");
        }

        return lots.stream()
                .map(LotRequestDto::getCig)
                .map(cig -> lotRepository.findById(cig)
                        .orElseThrow(() -> new EntityNotFoundException("Lot with CIG " + cig + " not found"))
                )
                .map(Lot::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    private Boolean isThreshold(TenderRequestDto request) {
        BigDecimal amount = calculateAmount(request);

        if (TenderType.NEGOTIATED_PROCEDURE.equals(request.getTenderType())) {
                return amount.compareTo(BigDecimal.valueOf(100_000)) > 0;
        }
        else if (TenderType.OPEN_PROCEDURE.equals(request.getTenderType())){
                return amount.compareTo(BigDecimal.valueOf(75_000)) > 0;
        }
        else{

            return null;
        }
    }

    private String generateProtocolId() {

        Tender tender = tenderRepository.findTopByOrderByProtocolIdDesc();

        if(tender == null){
            return "gara_n_1";
        }else{
            String[] parts = tender.getProtocolId().split("_");
            int lastNum = Integer.parseInt(parts[2]);
            int nextNum = lastNum +1;
            return "gara_n_" + nextNum;
        }


    }















}
