package med.supi.api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndDateTime(Long id, LocalDateTime dateTime);

    boolean existsByPatientIdAndDateTimeBetween(Long id, LocalDateTime firstTime, LocalDateTime lastTime);
}
