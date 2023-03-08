package med.supi.api.domain.patient;

public record PatientListDto (
    Long id, String name, String email, String uid
) {
    public PatientListDto(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getUid());
    }
}
