package med.supi.api.domain.appointment.validations.scheduling;

import jakarta.validation.ValidationException;
import med.supi.api.domain.appointment.AppointmentScheduleDto;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AdvancedTimeValidator implements AppointmentScheduleValidator {
    public void validate(AppointmentScheduleDto data) {
        var appointmentDate = data.dateTime();
        var now = LocalDateTime.now();
        var diffBetweenMinutes = Duration.between(now, appointmentDate).toMinutes();

        if (diffBetweenMinutes < 30)
            throw new ValidationException("Appointment must be schedule before 30 minutes");
    }
}
