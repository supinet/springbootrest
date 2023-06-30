package med.supi.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.supi.api.domain.appointment.AppointmentScheduleDto;
import med.supi.api.domain.doctor.DoctorRepository;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctorValidation implements AppointmentScheduleValidator {

    private DoctorRepository doctorRepository;
    public void validate(AppointmentScheduleDto data) {
        if (data.doctorId() == null)
            return;

        var activeDoctor = doctorRepository.findActiveById(data.doctorId());
        if (!activeDoctor)
            throw new ValidationException("Doctor is no longer active, please choose other");
    }
}
