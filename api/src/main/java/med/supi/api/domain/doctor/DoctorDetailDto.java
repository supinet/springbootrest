package med.supi.api.domain.doctor;

import med.supi.api.domain.address.Address;

public record DoctorDetailDto (
    Long id,
    String name,
    String email,
    String crm,
    String telephone,
    Specialty specialty,
    Address address
) {
    public DoctorDetailDto(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getTelephone(), doctor.getSpecialty(), doctor.getAddress());
    }
}
