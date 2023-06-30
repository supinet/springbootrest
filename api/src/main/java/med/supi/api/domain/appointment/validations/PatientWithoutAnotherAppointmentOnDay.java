package med.supi.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.supi.api.domain.appointment.AppointmentRepository;
import med.supi.api.domain.appointment.AppointmentScheduleDto;
import org.springframework.stereotype.Component;

@Component
public class PatientWithoutAnotherAppointmentOnDay implements AppointmentScheduleValidator {

    private AppointmentRepository appointmentRepository;

    public void validate(AppointmentScheduleDto data) {
        var firstTime = data.dateTime().withHour(7);
        var lastTime = data.dateTime().withHour(6);
        var patientHasAnotherAppointmentOnDay = appointmentRepository.existsByPatientIdAndDataBetween(data.patientId(), firstTime, lastTime);
        if (patientHasAnotherAppointmentOnDay)
            throw new ValidationException("There is an appointment schedule for this patient");
    }
}
