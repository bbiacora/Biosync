package persistence;

import model.Patient;
import model.Patients;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ReaderTest {
    Reader reader;

    @Test
    void testParsePatientsFile() {
        try {
            Patients patients = reader.readPatients(new File("./data/testPatients.txt"));
            assertEquals(patients.getPatientKeySet().size(), 2);
            Patient patient = patients.getPatient("56789");
            assertEquals(patient.getFirstName(), "Jane");
            assertEquals(patient.getLastName(), "Doe");
            assertEquals(patient.getDiagnoses().get(0), "ASTHMA");
            assertEquals(patient.getDiagnoses().get(1), "-");
            assertEquals(patient.getDiagnoses().get(2), "-");
            assertEquals(patient.getMedications().get(0), "FLUTICASONE");
            assertEquals(patient.getMedications().get(1), "-");
            assertEquals(patient.getMedications().get(2), "-");
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readPatients(new File("./data/fileDoesNotExist.txt"));
        } catch (IOException e) {
            // Expect
        }
    }
}