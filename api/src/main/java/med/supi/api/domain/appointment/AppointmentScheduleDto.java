package med.supi.api.domain.appointment;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.supi.api.domain.doctor.Specialty;

public record AppointmentScheduleDto (
    Long doctorId,
    
    @NotNull
    Long patientId,

    @NotNull
    @Future
    LocalDateTime dateTime,

    Specialty specialty
) {

}
