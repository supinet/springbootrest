package med.supi.api.domain.appointment.validations.scheduling;

import jakarta.validation.ValidationException;
import med.supi.api.domain.appointment.AppointmentScheduleDto;
import med.supi.api.domain.appointment.validations.scheduling.AppointmentScheduleValidator;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ClinicOpeningHoursValidator implements AppointmentScheduleValidator {
    public void validate(AppointmentScheduleDto data) {
        var dateAppointment = data.dateTime();
        var sunday = dateAppointment.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeClinicOpen = dateAppointment.getHour() < 7;
        var afterClinicClose = dateAppointment.getHour() > 18;

        if (sunday || beforeClinicOpen || afterClinicClose)
            throw new ValidationException("Clinic is closed");
    }
}
