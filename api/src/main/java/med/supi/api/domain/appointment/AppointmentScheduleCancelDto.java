package med.supi.api.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record AppointmentScheduleCancelDto (
        @NotNull
        Long appointmentId,

        @NotNull
        CancellationReason cancellationReason
) {
}
