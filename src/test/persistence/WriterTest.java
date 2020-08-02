package persistence;

import model.Patient;
import model.Patients;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class WriterTest {
    private static final String TEST_FILE = "./data/testPatients2.txt";
    private Writer writer;
    private Patients savedPatients;
    private Patient savedPatient;
    private ArrayList<String> diagnoses;
    private ArrayList<String> medications;

    @BeforeEach
    void setUp() throws FileNotFoundException, UnsupportedEncodingException {
        writer = new Writer(new File(TEST_FILE));
        diagnoses = new ArrayList<>();
        diagnoses.add("HYPERTENSION");
        diagnoses.add("ASTHMA");
        medications = new ArrayList<>();
        medications.add("FLUTICASONE");

        savedPatients = new Patients();
        savedPatient = new Patient("55555", "Rob", "Roberts",
                diagnoses, medications);
        savedPatients.addPatient(savedPatient);
    }

    @Test
    void testWritePatients() {
        writer.write(savedPatients);
        writer.close();

        try {
            Patients loadedPatients = Reader.readPatients(new File(TEST_FILE));
            Patient loadedPatient = loadedPatients.getPatient("55555");
            assertEquals(loadedPatient.getFirstName(), "Rob");
            assertEquals(loadedPatient.getLastName(), "Roberts");
            assertEquals(loadedPatient.getDiagnoses().get(0), "HYPERTENSION");
            assertEquals(loadedPatient.getDiagnoses().get(1), "ASTHMA");
            assertEquals(loadedPatient.getDiagnoses().get(2), "-");
            assertEquals(loadedPatient.getMedications().get(0), "FLUTICASONE");
            assertEquals(loadedPatient.getMedications().get(1), "-");
            assertEquals(loadedPatient.getMedications().get(2), "-");
        } catch (IOException e) {
            fail("Unexpected IOEcxception");
        }
    }
}