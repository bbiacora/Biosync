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
        assertTrue(patients.addPatient("0123456789", "John", "Smith"));
    }

    @Test
    public void testAddPatientNumberAlreadyRegistered() {
        patients.addPatient("0123456789", "John", "Smith");
        assertFalse(patients.addPatient("0123456789", "Jane", "Doe"));
    }

    @Test
    public void testRemovePatientInPatients() {
        String patientKey = "1111222333";
        patients.addPatient("0123456789", "John", "Smith");
        patients.addPatient("9876543210", "Jane", "Doe");
        patients.addPatient("1111222333", "James", "Johnson");
        assertTrue(patients.removePatient(patientKey));
    }

    @Test
    public void testRemovePatientNotInPatients() {
        String patientKey = "1111222333";
        patients.addPatient("0123456789", "John", "Smith");
        patients.addPatient("9876543210", "Jane", "Doe");
        assertFalse(patients.removePatient(patientKey));

    }

    @Test
    public void testGetPatientInPatients() {
        String patientKey = "0123456789";
        patients.addPatient("0123456789", "John", "Smith");
        patients.addPatient("1111222333", "James", "Johnson");
        assertTrue(patients.getPatient(patientKey).getFirstName().equals("John") &&
                patients.getPatient(patientKey).getLastName().equals("Smith"));
    }

    @Test
    public void testGetPatientNotInPatients() {
        String patientKey = "1111222333";
        patients.addPatient("0123456789", "John", "Smith");
        assertEquals(patients.getPatient(patientKey), null);
    }

    @Test
    public void testContainsPatientInPatients() {
        String patientKey = "1111222333";
        patients.addPatient("0123456789", "John", "Smith");
        patients.addPatient("9876543210", "Jane", "Doe");
        patients.addPatient("1111222333", "James", "Johnson");
        assertTrue(patients.containsPatient(patientKey));
    }

    @Test
    public void testContainsPatientNotInPatients() {
        String patientKey = "1111222333";
        patients.addPatient("0123456789", "John", "Smith");
        patients.addPatient("9876543210", "Jane", "Doe");
        assertFalse(patients.containsPatient(patientKey));
    }

}