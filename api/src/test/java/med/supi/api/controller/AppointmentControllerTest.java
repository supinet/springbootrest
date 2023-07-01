package med.supi.api.controller;

import med.supi.api.domain.appointment.AppointmentScheduleDetailDto;
import med.supi.api.domain.appointment.AppointmentScheduleDto;
import med.supi.api.domain.appointment.AppointmentService;
import med.supi.api.domain.doctor.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@WithMockUser
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AppointmentScheduleDto> appointmentScheduleDtoJson;

    @Autowired
    private JacksonTester<AppointmentScheduleDetailDto> appointmentScheduleDetailDtoJson;

    @MockBean
    private AppointmentService appointmentService;

    @Test
    @DisplayName("Should return http 400 code when data are invalid")
    void scheduleScenario1() throws Exception {
        var response = mvc.perform(post("/appointments"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return http 200 code when data are valid")
    void scheduleScenario2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGY;

        var appointmentScheduleDetailDto = new AppointmentScheduleDetailDto(null, 2l, 4l, date);
        when(appointmentService.schedule(any())).thenReturn(appointmentScheduleDetailDto);

        var response = mvc
                .perform(
                        post("/appointments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(appointmentScheduleDtoJson.write(
                                        new AppointmentScheduleDto(2l, 4l, date, specialty)
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var expectedJson = appointmentScheduleDetailDtoJson.write(
                appointmentScheduleDetailDto
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}