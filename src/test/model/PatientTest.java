package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {
    private PrintWriter printWriter;
    private Patient patient;
    private Patient otherPatient;
    private ArrayList<String> diagnoses;
    private ArrayList<String> medications;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        printWriter = new PrintWriter(new File("./data/patients.txt"));
        diagnoses = new ArrayList<>();
        medications = new ArrayList<>();

        patient = new Patient("01234", "John", "Smith");
        otherPatient = new Patient("55555", "Rob", "Roberts",
                diagnoses, medications);
    }

    @Test
    void testConstructorThreeParameters() {
        assertEquals(patient.getPersonalHealthNumber(), "01234");
        assertEquals(patient.getFirstName(), "John");
        assertEquals(patient.getLastName(), "Smith");
    }

    @Test
    void testConstructorFiveParameters() {
        assertEquals(otherPatient.getPersonalHealthNumber(), "55555");
        assertEquals(otherPatient.getFirstName(), "Rob");
        assertEquals(otherPatient.getLastName(), "Roberts");
        assertEquals(otherPatient.getDiagnoses(), diagnoses);
        assertEquals(otherPatient.getMedications(), medications);
    }

    @Test
    void testInitializeList() {
        assertEquals(patient.getDiagnoses().size(), 3);
        assertEquals(patient.getDiagnoses().get(0), "-");
        assertEquals(patient.getDiagnoses().get(1), "-");
        assertEquals(patient.getDiagnoses().get(2), "-");

        assertEquals(patient.getMedications().size(), 3);
        assertEquals(patient.getMedications().get(0), "-");
        assertEquals(patient.getMedications().get(1), "-");
        assertEquals(patient.getMedications().get(2), "-");
    }

    @Test
    public void testAddDiagnosisHasPlaceholder() {
        assertFalse(patient.getDiagnoses().contains("HYPERTENSION"));
        patient.addDiagnosis("Hypertension");
        assertTrue(patient.getDiagnoses().contains("HYPERTENSION"));
        assertTrue(patient.getDiagnoses().contains("-"));
    }

    @Test
    public void testAddDiagnosisNoPlaceholder() {
        patient.addDiagnosis("Hypertension");
        patient.addDiagnosis("Asthma");
        patient.addDiagnosis("Anxiety");
        assertFalse(patient.getDiagnoses().contains("-"));

        assertFalse(patient.getDiagnoses().contains("UTI"));
        patient.addDiagnosis("uti");
        assertFalse(patient.getDiagnoses().contains("UTI"));
    }

    @Test
    public void testRemoveDiagnosisInList() {
        patient.addDiagnosis("Hypertension");
        patient.addDiagnosis("Asthma");
        patient.addDiagnosis("Anxiety");
        assertTrue(patient.getDiagnoses().contains("HYPERTENSION"));
        assertFalse(patient.getDiagnoses().contains("-"));

        patient.removeDiagnosis("Hypertension");
        assertFalse(patient.getDiagnoses().contains("HYPERTENSION"));
        assertTrue(patient.getDiagnoses().contains("-"));
    }

    @Test
    public void testRemoveDiagnosisNotInList() {
        patient.addDiagnosis("Hypertension");
        patient.addDiagnosis("Asthma");
        patient.addDiagnosis("Anxiety");

        assertFalse(patient.getDiagnoses().contains("UTI"));
        patient.removeDiagnosis("uti");
        assertFalse(patient.getDiagnoses().contains("UTI"));
    }

    @Test
    public void testAddMedicationHasPlaceholder() {
        assertFalse(patient.getMedications().contains("BUMETANIDE"));
        patient.addMedication("bumetanide");

        assertTrue(patient.getMedications().contains("BUMETANIDE"));
        assertTrue(patient.getMedications().contains("-"));
    }

    @Test
    void testAddMedicationNoPlaceholder() {
        patient.addMedication("Bumetanide");
        patient.addMedication("Acebutolol");
        patient.addMedication("Fluoxetine");
        assertFalse(patient.getMedications().contains("-"));

        assertFalse(patient.getMedications().contains("PAROXETINE"));
        patient.addMedication("Paroxetine");
        assertFalse(patient.getMedications().contains("PAROXETINE"));
    }

    @Test
    void testRemoveMedicationInList() {
        patient.addMedication("Bumetanide");
        patient.addMedication("Acebutolol");
        patient.addMedication("Fluoxetine");
        assertTrue(patient.getMedications().contains("FLUOXETINE"));
        assertFalse(patient.getMedications().contains("-"));

        patient.removeMedication("Fluoxetine");
        assertFalse(patient.getMedications().contains("Fluoxetine"));
        assertTrue(patient.getMedications().contains("-"));
    }

    @Test
    public void testRemoveMedicationNotinList() {
        patient.addMedication("Bumetanide");
        patient.addMedication("Acebutolol");
        patient.addMedication("Fluoxetine");
        assertFalse(patient.getMedications().contains("-"));

        assertFalse(patient.getMedications().contains("PAROXETINE"));
        patient.removeMedication("Paroxetine");
        assertFalse(patient.getMedications().contains("PAROXETINE"));
    }

    @Test
    void testSave() {
        assertTrue(patient.save(printWriter));
        assertTrue(otherPatient.save(printWriter));
    }

    @Test
    void testSaveList() {
        diagnoses = new ArrayList<>();
        assertTrue(otherPatient.saveList(printWriter, diagnoses));
    }

    @Test
    void testMaintainListNotFull() {
        diagnoses.add("Hypertension");
        diagnoses.add("Asthma");

        assertFalse(diagnoses.contains("-"));
        patient.maintainList(diagnoses);
        assertTrue(diagnoses.contains("-"));
    }

    @Test
    void testMaintainListFull() {
        diagnoses.add("Hypertension");
        diagnoses.add("Asthma");
        diagnoses.add("Anxiety");

        assertFalse(diagnoses.contains("-"));
        patient.maintainList(diagnoses);
        assertFalse(diagnoses.contains("-"));
    }
}