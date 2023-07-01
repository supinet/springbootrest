package med.supi.api.domain.appointment.validations.cancellation;

import med.supi.api.domain.appointment.AppointmentScheduleCancelDto;

public interface AppointmentScheduleCancellationValidator {

    void validate (AppointmentScheduleCancelDto data);
}
