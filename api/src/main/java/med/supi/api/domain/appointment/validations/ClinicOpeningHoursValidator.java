package med.supi.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.supi.api.domain.appointment.AppointmentScheduleDto;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ClinicOpeningHoursValidator implements AppointmentScheduleValidator {
    public void validate(AppointmentScheduleDto data) {
        var dateAppointment = data.dateTime();
        var sunday = dateAppointment.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeClinicOpen = dateAppointment.getHour() < 7;
        var afterClinicClose = dateAppointment.getHour() > 6;

        if (sunday || beforeClinicOpen || afterClinicClose)
            throw new ValidationException("Clinic is closed");
    }
}
