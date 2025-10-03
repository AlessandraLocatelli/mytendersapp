package com.bolzanoprovince.tendering.model.dto.responses;
import com.bolzanoprovince.tendering.model.enums.EvaluationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


public class LotResponseDto {
    private String cig;
    private String description;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private EvaluationType evaluationType;
}
