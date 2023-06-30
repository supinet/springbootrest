package med.supi.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByActiveTrue(Pageable page);

    @Query("""
            select d from Doctor d
            where d.active = 1
            and d.specialty = :specialty
            and d.id not in (
                select a.doctor.id from Appointment a
                where a.dateTime = :dateTime
            )
            order by rand()
            limit 1
            """)
    Doctor chooseRandomDoctorOnDate(Specialty specialty, LocalDateTime dateTime);

    @Query("""
            select d.active from Doctor d
            where d.id = :id
            """)
    Boolean findActiveById(Long id);
}
