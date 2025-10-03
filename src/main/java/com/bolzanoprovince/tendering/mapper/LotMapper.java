package com.bolzanoprovince.tendering.mapper;

import com.bolzanoprovince.tendering.model.dto.requests.LotRequestDto;
import com.bolzanoprovince.tendering.model.dto.responses.LotResponseDto;
import com.bolzanoprovince.tendering.model.entity.Lot;
import com.bolzanoprovince.tendering.model.entity.Tender;

public class LotMapper {

    public static Lot toEntity(LotRequestDto request, Tender tender){

        Lot lot = new Lot();
        lot.setCig(request.getCig());
        lot.setAmount(request.getAmount());
        lot.setDescription(request.getDescription());
        lot.setEvaluationType(request.getEvaluationType());
        lot.setTender(tender);

        return lot;

    }

    public static LotResponseDto toLotResponseDto (Lot entity){

        LotResponseDto response = new LotResponseDto();
        response.setCig(entity.getCig());
        response.setDescription(entity.getDescription());
        response.setAmount(entity.getAmount());
        response.setEvaluationType(entity.getEvaluationType());


        return response;


    }

}
