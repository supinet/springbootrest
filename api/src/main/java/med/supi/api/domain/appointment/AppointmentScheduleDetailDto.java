package med.supi.api.domain.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.supi.api.domain.doctor.Specialty;

import java.time.LocalDateTime;

public record AppointmentScheduleDetailDto(
    Long doctorId,

    @NotNull
    Long patientId,

    @NotNull
    @Future
    LocalDateTime dateTime,

    Specialty specialty
) { }
