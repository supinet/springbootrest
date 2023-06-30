package med.supi.api.controller;

import med.supi.api.domain.appointment.AppointmentScheduleDetailDto;
import med.supi.api.domain.appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import med.supi.api.domain.appointment.AppointmentScheduleDto;

@Controller
@RequestMapping("appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointment;
    
    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid AppointmentScheduleDto data) {
        appointment.schedule(data);
        return ResponseEntity.ok(new AppointmentScheduleDetailDto(null, null, null, null));
    }

}
