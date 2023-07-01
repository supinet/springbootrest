package med.supi.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import med.supi.api.domain.appointment.AppointmentScheduleDetailDto;
import med.supi.api.domain.appointment.AppointmentService;
import med.supi.api.domain.appointment.AppointmentScheduleCancelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import med.supi.api.domain.appointment.AppointmentScheduleDto;

@RestController
@RequestMapping("appointments")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    @Autowired
    private AppointmentService appointment;
    
    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid AppointmentScheduleDto data) {
        AppointmentScheduleDetailDto schedule = appointment.schedule(data);
        return ResponseEntity.ok(schedule);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid AppointmentScheduleCancelDto data) {
        appointment.cancel(data);
        return ResponseEntity.noContent().build();
    }

}
