package med.supi.api.domain.appointment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.supi.api.domain.doctor.Doctor;
import med.supi.api.domain.patient.Patient;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;

@Table(name = "appointment")
@Entity(name = "Appointment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "datetime")
    private LocalDateTime dateTime;

    @Column(name = "cancellation_reason")
    @Enumerated(EnumType.STRING)
    private CancellationReason cancellationReason;

    public void cancel(CancellationReason cancellationReason) {
        this.cancellationReason = cancellationReason;
    }
}
