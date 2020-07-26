package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {
    private Patient patient;

    @BeforeEach
    public void setUp() {
        patient = new Patient("01234", "John", "Smith");
    }

    @Test
    public void testAddDiagnosis() {
        patient.addDiagnosis("Hypertension");
        assertTrue(patient.getDiagnoses().contains("Hypertension"));
    }

    @Test
    public void testAddMedication() {
        patient.addMedication("Bumetanide");
        assertTrue(patient.getMedications().contains("Bumetanide"));
    }

    @Test
    public void testRemoveMedication() {
        patient.addMedication("Bumetanide");
        patient.addMedication("Furosemide");
        patient.addMedication("Acebutolol");
        assertTrue(patient.getMedications().contains("Furosemide"));
        patient.removeMedication("Furosemide");
        assertFalse(patient.getMedications().contains("Furosemide"));
    }

    @Test
    public void testGetPersonalHealthNumber() {
        assertEquals(patient.getPersonalHealthNumber(), "01234");
    }

    @Test
    public void testGetFirstName() {
        assertEquals(patient.getFirstName(), "John");
    }

    @Test
    public void testGetLastName() {
        assertEquals(patient.getLastName(), "Smith");
    }
}