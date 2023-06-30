package med.supi.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.supi.api.domain.appointment.AppointmentRepository;
import med.supi.api.domain.appointment.AppointmentScheduleDto;
import org.springframework.stereotype.Component;

@Component
public class MedicalWithAnotherAppointmentAtTheSameTimeValidator implements AppointmentScheduleValidator {
    private AppointmentRepository appointmentRepository;

    public void validate(AppointmentScheduleDto data) {
        var doctorWithAnotherAppointmentAtTheSameTime = appointmentRepository.existsByDoctorIdAndDateTime(data.doctorId(), data.dateTime());
        if (doctorWithAnotherAppointmentAtTheSameTime)
            throw new ValidationException("The doctor already has a appointment in this time");
    }
}

