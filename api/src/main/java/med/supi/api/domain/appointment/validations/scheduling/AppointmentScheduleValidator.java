package med.supi.api.domain.appointment.validations.scheduling;

import med.supi.api.domain.appointment.AppointmentScheduleDto;

public interface AppointmentScheduleValidator {
    void validate(AppointmentScheduleDto data);
}
