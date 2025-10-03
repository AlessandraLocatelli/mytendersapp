package com.bolzanoprovince.tendering.model.dto.requests;

import com.bolzanoprovince.tendering.model.enums.EvaluationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class LotRequestDto {
    @NotBlank(message = "Il CIG è obbligatorio")
    @Size(min = 10, max = 10, message = "Il CIG deve contenere esattamente 10 caratteri")
    @Pattern(regexp = "^[A-Z0-9]{10}$", message = "Il CIG deve contenere solo lettere maiuscole e numeri")
    private String cig;

    @NotBlank(message = "La descrizione è obbligatoria")
    @Size(max = 10, message = "Massimo 10 caratteri")
    private String description;

    @NotNull(message = "l'importo è obbligatorio")
    @DecimalMin(value = "0.01", inclusive = true, message = "L'importo deve essere maggiore di zero")
    @Digits(integer = 12, fraction = 2, message = "L'importo può avere al massimo 12 cifre intere e 2 decimali")
    private BigDecimal amount;

    @NotNull(message = "indicare il tipo di lotto")
    @Enumerated(EnumType.STRING)
    private EvaluationType evaluationType;

    @NotNull(message = "indicare la gara associata")
    private String tenderId;

}
