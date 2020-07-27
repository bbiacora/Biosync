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
        assertEquals(patient.getDiagnoses().size(), 0);
        patient.addDiagnosis("Hypertension");
        assertEquals(patient.getDiagnoses().size(), 1);
        assertTrue(patient.getDiagnoses().contains("Hypertension"));
    }

    @Test
    public void testRemoveDiagnosisCaseMatch() {
        patient.addDiagnosis("Hypertension");
        patient.addDiagnosis("Asthma");
        assertTrue(patient.getDiagnoses().contains("Hypertension"));
        patient.removeDiagnosis("Hypertension");
        assertFalse(patient.getMedications().contains("Hypertension"));
    }

    @Test
    public void testRemoveDiagnosisCaseNotMatch() {
        patient.addDiagnosis("Hypertension");
        patient.addDiagnosis("Asthma");
        assertTrue(patient.getDiagnoses().contains("Hypertension"));
        patient.removeDiagnosis("Hypertension");
        assertFalse(patient.getMedications().contains("hyPertension"));
    }

    @Test
    public void testRemoveDiagnosisNotInList() {
        patient.addDiagnosis("Hypertension");
        assertFalse(patient.getDiagnoses().contains("Asthma"));
        patient.removeDiagnosis("Asthma");
        assertFalse(patient.getMedications().contains("Asthma"));
    }

    @Test
    public void testAddMedication() {
        assertEquals(patient.getMedications().size(), 0);
        patient.addMedication("Bumetanide");
        assertEquals(patient.getMedications().size(), 1);
        assertTrue(patient.getMedications().contains("Bumetanide"));
    }

    @Test
    public void testRemoveMedicationCaseMatch() {
        patient.addMedication("Bumetanide");
        patient.addMedication("Furosemide");
        patient.addMedication("Acebutolol");
        assertTrue(patient.getMedications().contains("Furosemide"));
        patient.removeMedication("Furosemide");
        assertFalse(patient.getMedications().contains("Furosemide"));
    }

    @Test
    public void testRemoveMedicationCaseNotMatch() {
        patient.addMedication("Bumetanide");
        patient.addMedication("Furosemide");
        patient.addMedication("Acebutolol");
        assertTrue(patient.getMedications().contains("Furosemide"));
        patient.removeMedication("furosEmide");
        assertFalse(patient.getMedications().contains("Furosemide"));
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