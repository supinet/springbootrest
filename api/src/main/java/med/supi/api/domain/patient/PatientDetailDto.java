package med.supi.api.domain.patient;

import med.supi.api.domain.address.Address;

public record PatientDetailDto (
    Long id, String name, String email, String uid, String telephone, Address address
) {
    public PatientDetailDto(Patient patient) {
        this(patient.getId(),
        patient.getName(),
        patient.getEmail(),
        patient.getUid(),
        patient.getTelephone(),
        patient.getAddress());
    }
}
