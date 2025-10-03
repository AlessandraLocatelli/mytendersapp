package com.bolzanoprovince.tendering.model.dto.responses;
import com.bolzanoprovince.tendering.model.enums.TenderType;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


public class TenderResponseDto {
    private String protocolId;
    private String description;
    private BigDecimal amount;
    private Boolean threshold;
    private Boolean publicVisibility;
    private LocalDateTime docAvailableUntilQualification;
    @Enumerated(EnumType.STRING)
    private TenderType tenderType;
    private List<LotResponseDto> lots;
    private String responsibleFullName;
    private Set<String> vendors;

}
