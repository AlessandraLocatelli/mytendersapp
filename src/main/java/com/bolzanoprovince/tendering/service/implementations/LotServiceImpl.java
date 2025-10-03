package com.bolzanoprovince.tendering.service.implementations;

import com.bolzanoprovince.tendering.mapper.LotMapper;
import com.bolzanoprovince.tendering.model.dto.requests.LotRequestDto;
import com.bolzanoprovince.tendering.model.dto.responses.LotResponseDto;
import com.bolzanoprovince.tendering.model.entity.Lot;
import com.bolzanoprovince.tendering.model.entity.Tender;
import com.bolzanoprovince.tendering.repository.LotRepository;
import com.bolzanoprovince.tendering.repository.TenderRepository;
import com.bolzanoprovince.tendering.service.interfaces.LotService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
@Transactional //there are methods that modify the db (ex. delete)
public class LotServiceImpl implements LotService {

    private final TenderRepository tenderRepository;
    private final LotRepository lotRepository;

    @Override
    public LotResponseDto createLot(LotRequestDto request) {

        Tender tender = tenderRepository.findById(request.getTenderId())
                .orElseThrow(() -> new EntityNotFoundException("Tender with id "+request.getTenderId()+" not found"));

        Lot entity = LotMapper.toEntity(request,tender);
        Lot savedEntity = lotRepository.save(entity);
        //ricalcolo importo gara perchè aggiunto un nuovo lotto
        BigDecimal newAmount = lotRepository.sumByTenderId(tender.getProtocolId());
        //risalvare il tender con il nuovo amount
        tender.setAmount(newAmount);
        tenderRepository.save(tender);

        return LotMapper.toLotResponseDto(savedEntity);
    }

    @Override
    public LotResponseDto getLotById(String cig) {

        Lot lot = lotRepository.findById(cig)
                .orElseThrow(() -> new EntityNotFoundException("Lot with cig "+cig+" not found"));

        return LotMapper.toLotResponseDto(lot);

    }

    @Override
    public List<LotResponseDto> getAllLots() {

        List<Lot> lots = lotRepository.findAll();

        return lots
                .stream()
                .map(LotMapper::toLotResponseDto)
                .collect(Collectors.toList());


    }

    @Override
    public void deleteLotById(String cig) {

        Lot lot = lotRepository.findById(cig)
                .orElseThrow(() -> new EntityNotFoundException("Lot with cig "+cig+" not found"));

        lotRepository.delete(lot);
    }

    @Override
    public LotResponseDto updateLot(String cig, LotRequestDto request) {

        Lot lot = lotRepository
                .findById(cig)
                .orElseThrow(() -> new EntityNotFoundException("Lot with cig "+cig+" not found"));

        Tender tender = tenderRepository.findById(request.getTenderId())
                .orElseThrow(() -> new EntityNotFoundException("Tender with id "+request.getTenderId()+" not found"));

        lotRepository.save(LotMapper.toEntity(request,tender));

        return LotMapper.toLotResponseDto(lot);



    }


}
