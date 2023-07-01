package med.supi.api.domain.appointment.validations.scheduling;

import jakarta.validation.ValidationException;
import med.supi.api.domain.appointment.AppointmentRepository;
import med.supi.api.domain.appointment.AppointmentScheduleDto;
import med.supi.api.domain.appointment.validations.scheduling.AppointmentScheduleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientWithoutAnotherAppointmentOnDay implements AppointmentScheduleValidator {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(AppointmentScheduleDto data) {
        var firstTime = data.dateTime().withHour(7);
        var lastTime = data.dateTime().withHour(18);
        var patientHasAnotherAppointmentOnDay = appointmentRepository.existsByPatientIdAndDateTimeBetween(data.patientId(), firstTime, lastTime);
        if (patientHasAnotherAppointmentOnDay)
            throw new ValidationException("There is an appointment schedule for this patient");
    }
}
