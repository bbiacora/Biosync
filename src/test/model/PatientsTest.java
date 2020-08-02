package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

class PatientsTest {
    private PrintWriter printWriter;
    private Patients patients;
    private Patient patient;


    @BeforeEach
    void setUp() throws FileNotFoundException {
        patients = new Patients();
        patient = new Patient("55555", "Rob", "Roberts");
        printWriter = new PrintWriter(new File("./data/patients.txt"));
    }

    @Test
    public void testAddPatientThreeParameters() {
        assertFalse(patients.getPatientKeySet().contains("01234"));
        patients.addPatient("01234", "John", "Smith");
        assertTrue(patients.getPatientKeySet().contains("01234"));
    }

    @Test
    void testAddPatientOneParamenter() {
        assertFalse(patients.getPatientKeySet().contains("55555"));
        patients.addPatient(patient);
        assertTrue(patients.getPatientKeySet().contains("55555"));
    }

    @Test
    void testRemovePatient() {
        String patientKey = "11112";
        patients.addPatient("01234", "John", "Smith");
        patients.addPatient("987650", "Jane", "Doe");
        patients.addPatient("11112", "James", "Johnson");
        assertTrue(patients.containsPatient(patientKey));
        patients.removePatient("11112");
        assertFalse(patients.containsPatient(patientKey));
    }

    @Test
    void testGetPatientInPatients() {
        String patientKey = "012345";
        patients.addPatient("012345", "John", "Smith");
        patients.addPatient("11112", "James", "Johnson");
        assertTrue(patients.getPatient(patientKey).getFirstName().equals("John") &&
                patients.getPatient(patientKey).getLastName().equals("Smith"));
    }

    @Test
    void testGetPatientNotInPatients() {
        String patientKey = "11112";
        patients.addPatient("01234", "John", "Smith");
        assertNull(patients.getPatient(patientKey));
    }

    @Test
    void testGetPatientKeySet() {
        assertFalse(patients.getPatientKeySet().contains("01234"));
        assertFalse(patients.getPatientKeySet().contains("98765"));
        patients.addPatient("01234", "John", "Smith");
        patients.addPatient("98765", "Jane", "Doe");
        assertTrue(patients.getPatientKeySet().contains("01234"));
        assertTrue(patients.getPatientKeySet().contains("98765"));
    }

    @Test
    void testContainsPatientInPatients() {
        String patientKey = "11112";
        assertFalse(patients.containsPatient(patientKey));
        patients.addPatient("01234", "John", "Smith");
        patients.addPatient("98765", "Jane", "Doe");
        patients.addPatient("11112", "James", "Johnson");
        assertTrue(patients.containsPatient(patientKey));
    }

    @Test
    void testContainsPatientNotInPatients() {
        String patientKey = "11112";
        patients.addPatient("01234", "John", "Smith");
        patients.addPatient("98765", "Jane", "Doe");
        assertFalse(patients.containsPatient(patientKey));
    }

    @Test
    void testSave() {
        patients.addPatient(patient);
        assertTrue(patients.save(printWriter));
    }
}