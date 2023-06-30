package med.supi.api.domain.appointment;

import med.supi.api.domain.ExceptionValidation;
import med.supi.api.domain.appointment.validations.AppointmentScheduleValidator;
import med.supi.api.domain.doctor.Doctor;
import med.supi.api.domain.doctor.DoctorRepository;
import med.supi.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<AppointmentScheduleValidator> validators;
    public void schedule(AppointmentScheduleDto data) {

        if (!patientRepository.existsById(data.patientId()))
            throw new ExceptionValidation("Patient id not found");

        if (data.doctorId() != null && !doctorRepository.existsById(data.doctorId())) {
            throw new ExceptionValidation("Doctor id not found");
        }

        validators.forEach(v -> v.validate(data));

        var doctor = chooseDoctor(data);
        var patient = patientRepository.getReferenceById(data.patientId());
        var appointment = new Appointment(null, doctor, patient, data.dateTime());
        appointmentRepository.save(appointment);
    }

    private Doctor chooseDoctor(AppointmentScheduleDto data) {
        if (data.doctorId() != null)
            return doctorRepository.getReferenceById(data.doctorId());

        if (data.specialty() == null)
            throw new ExceptionValidation("Specialty is mandatory when doctor is not informed");

        return doctorRepository.chooseRandomDoctorOnDate(data.specialty(), data.dateTime());
    }

}
