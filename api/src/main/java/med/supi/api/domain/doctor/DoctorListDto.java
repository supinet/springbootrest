package med.supi.api.domain.doctor;

public record DoctorListDto(
    Long id,
    String name,
    String email,
    String crm,
    Specialty specialty
) {
    public DoctorListDto(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
