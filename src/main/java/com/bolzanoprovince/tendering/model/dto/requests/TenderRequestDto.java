package com.bolzanoprovince.tendering.model.dto.requests;

import com.bolzanoprovince.tendering.model.enums.TenderType;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


public class TenderRequestDto {
    @NotBlank(message = "La descrizione è obbligatoria")
    @Size(max = 10, message = "Massimo 10 caratteri")
    private String description;

    @NotNull(message= "Indicare se pubblica")
    private Boolean publicVisibility;

    @NotNull
    @FutureOrPresent(message = "La data può essere solo presente o futura")
    private LocalDateTime docAvailableUntilQualification;

    @NotNull (message = "Indicare la tipologia")
    private TenderType tenderType;

    @NotEmpty(message = "Indicare almeno un lotto")
    private List<LotRequestDto> lots;

    @NotNull (message = "Indicare un responsabile")
    private Long responsibleId;

    private Set<Long> vendorsId;
}
