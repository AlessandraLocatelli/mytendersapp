package com.bolzanoprovince.tendering.mapper;

import com.bolzanoprovince.tendering.model.dto.responses.VendorResponseDto;
import com.bolzanoprovince.tendering.model.entity.Vendor;


public class VendorMapper {


    public static VendorResponseDto toVendorResponseDto(Vendor entity){

        VendorResponseDto vendorResponseDto = new VendorResponseDto();
        vendorResponseDto.setName(entity.getName());


        return vendorResponseDto;
    }


}
