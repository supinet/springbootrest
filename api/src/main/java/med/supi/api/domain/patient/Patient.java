package med.supi.api.domain.patient;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.supi.api.domain.address.Address;

@Table(name = "patient")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String uid;

    @Embedded
    private Address address;

    private Boolean active;

    public Patient(PatientRegisterDto data) {
        this.name = data.name();
        this.email = data.email();
        this.telephone = data.telephone();
        this.uid = data.uid();
        this.address = new Address(data.address());
        this.active = true;
    }

    public void updatePatient(PatientUpdateDto data) {
        if (data.name() != null)
            this.name = data.name();

        if (data.telephone() != null)
            this.telephone = data.telephone();

        if (data.address() != null)
            this.address.updateAddress(data.address());
    }

    public void delete() {
        this.active = false;
    }

}
