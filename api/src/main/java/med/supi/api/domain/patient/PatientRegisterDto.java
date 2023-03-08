package med.supi.api.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.supi.api.domain.address.AddressRecord;

public record PatientRegisterDto(
    @NotBlank
    String name,
    
    @NotBlank
    @Email
    String email,

    @NotBlank
    String telephone,

    @NotBlank
    String uid,

    @NotNull
    @Valid
    AddressRecord address

) { }
