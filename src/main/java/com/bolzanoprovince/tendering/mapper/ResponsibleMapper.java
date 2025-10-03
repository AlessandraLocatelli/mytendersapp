package com.bolzanoprovince.tendering.mapper;

import com.bolzanoprovince.tendering.model.dto.responses.ResponsibleResponseDto;
import com.bolzanoprovince.tendering.model.entity.Responsible;


public class ResponsibleMapper {

   public static ResponsibleResponseDto toResponsibleResponseDto (Responsible entity){

       ResponsibleResponseDto response = new ResponsibleResponseDto();
       response.setFullName(entity.getFirstName()+" "+entity.getLastName());

       return response;
   }


}
