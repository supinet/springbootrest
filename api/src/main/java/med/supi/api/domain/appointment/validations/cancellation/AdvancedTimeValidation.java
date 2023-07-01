package med.supi.api.domain.appointment.validations.cancellation;

import med.supi.api.domain.ExceptionValidation;
import med.supi.api.domain.appointment.AppointmentRepository;
import med.supi.api.domain.appointment.AppointmentScheduleCancelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("AdvancedTimeCancellationValidator")
public class AdvancedTimeValidation implements AppointmentScheduleCancellationValidator {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void validate(AppointmentScheduleCancelDto data) {
        var appointment = appointmentRepository.getReferenceById(data.appointmentId());
        var now = LocalDateTime.now();
        var diffInHours = Duration.between(now, appointment.getDateTime()).toHours();

        if (diffInHours < 24)
            throw new ExceptionValidation("The appointment only can be canceled with at least 24h in advance!");
    }
}
