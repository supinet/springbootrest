package med.supi.api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Boolean existsByDoctorIdAndDateTime(Long id, LocalDateTime dateTime);

    @Query("""
            select a.patient.id from Appointment a
            where a.dateTime between :firstTime and :lastTime
            and a.patient.id = :id
            """)
    Boolean existsByPatientIdAndDataBetween(Long id, LocalDateTime firstTime, LocalDateTime lastTime);
}
