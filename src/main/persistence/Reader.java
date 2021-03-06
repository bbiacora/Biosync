package persistence;

import model.Patient;
import model.Patients;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Represents a reader that can read patient data from a file
// Reference: https://github.students.cs.ubc.ca/CPSC210/TellerApp
public class Reader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns patients parsed from file;
    //          throws IOException if an exception is raised when opening / reading from file
    public static Patients readPatients(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    //          containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns patients parsed from list of strings
    //          where each string contains data for one patient
    private static Patients parseContent(List<String> filePatientContent) {
        Patients patients = new Patients();
        ArrayList<Patient> patientsArray = new ArrayList<>();

        for (String line : filePatientContent) {
            ArrayList<String> lineComponents = splitString(line);
            patientsArray.add(parsePatient(lineComponents));
        }

        for (Patient patient : patientsArray) {
            patients.addPatient(patient);
        }

        return patients;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 9 where
    //           element 0 represents the patient's personal health number,
    //           element 1 represents the patient's first name,
    //           element 2 represents the patient's last name,
    //           elements 3-5 represents the patient's diagnosis records,
    //           element 6-8 represents the patient's medication records
    // EFFECTS: returns a patient constructed from components
    private static Patient parsePatient(List<String> components) {
        ArrayList<String> diagnoses = new ArrayList<>();
        ArrayList<String> medications = new ArrayList<>();

        String personalHealthNumber = components.get(0);
        String firstName = components.get(1);
        String lastName = components.get(2);

        diagnoses.add(components.get(3));
        diagnoses.add(components.get(4));
        diagnoses.add(components.get(5));

        medications.add(components.get(6));
        medications.add(components.get(7));
        medications.add(components.get(8));

        return new Patient(personalHealthNumber, firstName, lastName, diagnoses, medications);
    }
}
