package med.supi.api.domain.appointment.validations;

import med.supi.api.domain.appointment.AppointmentScheduleDto;

public interface AppointmentScheduleValidator {
    void validate(AppointmentScheduleDto data);
}
