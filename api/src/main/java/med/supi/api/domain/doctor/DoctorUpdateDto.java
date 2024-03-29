package med.supi.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.supi.api.domain.address.AddressDto;

public record DoctorUpdateDto (
    
    @NotNull
    Long id,
    String name,
    String telephone,
    AddressDto address
) {

}
