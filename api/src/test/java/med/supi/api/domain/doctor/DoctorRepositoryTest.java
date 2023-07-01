package med.supi.api.domain.doctor;

import med.supi.api.domain.address.AddressDto;
import med.supi.api.domain.appointment.Appointment;
import med.supi.api.domain.patient.Patient;
import med.supi.api.domain.patient.PatientRegisterDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    @DisplayName("Should return null when the unique doctor is not available on date")
    void chooseRandomDoctorOnDateTimeScenario1() {
        //given
        var nextMondayAt10AM = getNextMondayAt10AM();
        var doctor = getDoctorJhoe();
        var patient = registerPatient("MacFee", "mac@fee.com", "975635493857");
        registerAppointment(doctor, patient, nextMondayAt10AM);

        //when
        var availableDoctor = doctorRepository.chooseRandomDoctorOnDate(Specialty.CARDIOLOGY, nextMondayAt10AM);

        //then
        assertThat(availableDoctor).isNull();
    }

    private Doctor getDoctorJhoe() {
        return registerDoctor("Jhoe", "jhoe@supi.med", "123456", Specialty.CARDIOLOGY);
    }

    @Test
    @DisplayName("Should return a doctor when he is available on date and time")
    void chooseRandomDoctorOnDateTimeScenario2() {
        //given
        var nextMondayAt10AM = getNextMondayAt10AM();
        var doctorJhoe = getDoctorJhoe();

        //when
        var availableDoctor = doctorRepository.chooseRandomDoctorOnDate(Specialty.CARDIOLOGY, nextMondayAt10AM);

        //then
        assertThat(availableDoctor).isEqualTo(doctorJhoe);
    }

    private static LocalDateTime getNextMondayAt10AM() {
        return LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
    }

    private void registerAppointment(Doctor doctor, Patient patient, LocalDateTime dateTime) {
        em.persist(new Appointment(null, doctor, patient, dateTime, null));
    }

    private Doctor registerDoctor(String name, String email, String crm, Specialty specialty) {
        var doctor = new Doctor(doctorData(name, email, crm, specialty));
        em.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String uuid) {
        var patient = new Patient(patientData(name, email, uuid));
        em.persist(patient);
        return patient;
    }

    private DoctorRegisterDto doctorData(String name, String email, String crm, Specialty specialty) {
        return new DoctorRegisterDto(
                name,
                email,
                "61999999999",
                crm,
                specialty,
                addressData()
        );
    }

    private PatientRegisterDto patientData(String name, String email, String uuid) {
        return new PatientRegisterDto(
                name,
                email,
                "61999999999",
                uuid,
                addressData()
        );
    }

    private AddressDto addressData() {
        return new AddressDto(
                "Xpto Street",
                "Neighborhood",
                "00000000",
                "Dallas",
                "DA",
                "123",
                "DA"
        );
    }
}