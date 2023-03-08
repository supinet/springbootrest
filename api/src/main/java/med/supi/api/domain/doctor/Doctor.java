package med.supi.api.domain.doctor;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.supi.api.domain.address.Address;
import jakarta.persistence.GenerationType;

@Table(name = "doctor")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String crm;
    private Boolean active;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    public Doctor(DoctorRegisterDto doctor) {
        this.name = doctor.name();
        this.email = doctor.email();
        this.telephone = doctor.telephone();
        this.crm = doctor.crm();
        this.active = true;
        this.specialty = doctor.specialty();
        this.address = new Address(doctor.address());
    }

    public void updateDoctor(DoctorUpdateDto doctor) {
        if (doctor.name() != null)
            this.name = doctor.name();
        
        if (doctor.telephone() != null)
            this.telephone = doctor.telephone();

        if (doctor.address() != null)
            this.address.updateAddress(doctor.address());
    }

    public void delete() {
        this.active = false;
    }
}
