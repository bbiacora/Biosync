package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientsTest {
    private Patients patients;

    @BeforeEach
    public void setUp() {
        patients = new Patients();
    }

    @Test
    public void testAddPatientNumberNotRegistered() {
        assertTrue(patients.addPatient("01234", "John", "Smith"));
    }

    @Test
    public void testAddPatientNumberAlreadyRegistered() {
        patients.addPatient("01234", "John", "Smith");
        assertFalse(patients.addPatient("01234", "Jane", "Doe"));
    }

    @Test
    public void testRemovePatient() {
        String patientKey = "11112";
        patients.addPatient("01234", "John", "Smith");
        patients.addPatient("987650", "Jane", "Doe");
        patients.addPatient("11112", "James", "Johnson");
        assertTrue(patients.containsPatient(patientKey));
        patients.removePatient("11112");
        assertFalse(patients.containsPatient(patientKey));
    }

    @Test
    public void testGetPatientInPatients() {
        String patientKey = "012345";
        patients.addPatient("012345", "John", "Smith");
        patients.addPatient("11112", "James", "Johnson");
        assertTrue(patients.getPatient(patientKey).getFirstName().equals("John") &&
                patients.getPatient(patientKey).getLastName().equals("Smith"));
    }

    @Test
    public void testGetPatientNotInPatients() {
        String patientKey = "11112";
        patients.addPatient("01234", "John", "Smith");
        assertEquals(patients.getPatient(patientKey), null);
    }

    @Test
    public void testGetPatientKeySet() {
        assertFalse(patients.getPatientKeySet().contains("01234"));
        assertFalse(patients.getPatientKeySet().contains("98765"));
        patients.addPatient("01234", "John", "Smith");
        patients.addPatient("98765", "Jane", "Doe");
        assertTrue(patients.getPatientKeySet().contains("01234"));
        assertTrue(patients.getPatientKeySet().contains("98765"));
    }

    @Test
    public void testContainsPatientInPatients() {
        String patientKey = "11112";
        assertFalse(patients.containsPatient(patientKey));
        patients.addPatient("01234", "John", "Smith");
        patients.addPatient("98765", "Jane", "Doe");
        patients.addPatient("11112", "James", "Johnson");
        assertTrue(patients.containsPatient(patientKey));
    }

    @Test
    public void testContainsPatientNotInPatients() {
        String patientKey = "11112";
        patients.addPatient("01234", "John", "Smith");
        patients.addPatient("98765", "Jane", "Doe");
        assertFalse(patients.containsPatient(patientKey));
    }

}