package med.supi.api.domain.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.supi.api.domain.doctor.Specialty;

import java.time.LocalDateTime;

public record AppointmentScheduleDetailDto (
    Long id,

    Long doctorId,

    @NotNull
    Long patientId,

    @NotNull
    @Future
    LocalDateTime dateTime
) {
    public AppointmentScheduleDetailDto(Appointment appointment) {
        this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatient().getId(), appointment.getDateTime());
    }
}
