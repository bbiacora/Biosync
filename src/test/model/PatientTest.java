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
        assertFalse(patient.getDiagnoses().contains("HYPERTENSION"));
        patient.addDiagnosis("Hypertension");
        assertTrue(patient.getDiagnoses().contains("HYPERTENSION"));
    }

    @Test
    public void testRemoveDiagnosis() {
        patient.addDiagnosis("HYPERTENSION");
        assertTrue(patient.getDiagnoses().contains("HYPERTENSION"));
        patient.removeDiagnosis("Hypertension");
        assertFalse(patient.getDiagnoses().contains("HYPERTENSION"));
    }

    @Test
    public void testAddMedication() {
        assertFalse(patient.getMedications().contains("BUMETANIDE"));
        patient.addMedication("bumetanide");
        assertTrue(patient.getMedications().contains("BUMETANIDE"));
    }

    @Test
    public void testRemoveMedicationNotInList() {
        patient.addMedication("Bumetanide");
        patient.addMedication("Acebutolol");
        assertFalse(patient.getMedications().contains("Furosemide"));
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