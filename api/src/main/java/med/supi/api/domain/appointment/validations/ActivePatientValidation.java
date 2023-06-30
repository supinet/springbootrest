package med.supi.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.supi.api.domain.appointment.AppointmentScheduleDto;
import med.supi.api.domain.patient.PatientRepository;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidation implements AppointmentScheduleValidator {

    private PatientRepository patientRepository;

    public void validate(AppointmentScheduleDto data) {
        var isActivePatient = patientRepository.findActiveById(data.patientId());
        if (!isActivePatient)
            throw new ValidationException("Appointment can't be schedule with inactive patient");
    }
}
