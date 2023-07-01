package med.supi.api.domain.patient;

import jakarta.validation.constraints.NotNull;
import med.supi.api.domain.address.AddressDto;

public record PatientUpdateDto (

    @NotNull
    Long id,
    String name,
    String telephone,
    AddressDto address
    
) {
    
}
