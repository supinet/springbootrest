package med.supi.api.domain.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressRecord(
    
    @NotBlank
    String street,
    
    @NotBlank
    String neighborhood,

    @Pattern(regexp = "\\d{8}")
    String zipcode,

    @NotBlank
    String city,

    String complement,
    
    @NotBlank
    String number,
    
    @NotBlank
    String uf
    ) {
}
