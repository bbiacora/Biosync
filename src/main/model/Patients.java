package model;

import persistence.Reader;
import persistence.Savable;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

// Represents a map of patients to be handled by Biosync
public class Patients implements Savable {
    private HashMap<String, Patient> patients;

    //EFFECTS: initializes patients as an empty hashmap
    public Patients() {
        patients = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: creates a new patient and adds it to patients;
    //          returns true if there is no patient assigned to personal health number,
    //          otherwise returns false
    public void addPatient(String personalHealthNumber, String firstName, String lastName) {
        Patient patient = new Patient(personalHealthNumber, firstName, lastName);
        patients.put(personalHealthNumber, patient);
    }

    // MODIFIES: this
    // EFFECTS: removes a patient in patients with the corresponding personal health number
    public void removePatient(String personalHealthNumber) {
        patients.remove(personalHealthNumber);
    }

    // EFFECTS: returns a patient, if patient is in patients
    //          otherwise, returns null
    public Patient getPatient(String personalHealthNumber) {
        if (containsPatient(personalHealthNumber)) {
            return patients.get(personalHealthNumber);
        } else {
            return null;
        }
    }

    // EFFECTS: returns patient key set
    public Set<String> getPatientKeySet() {
        return patients.keySet();
    }

    // EFFECTS: returns true if there is a patient in patients under
    //          the given personal health number otherwise, returns false
    public boolean containsPatient(String personalHealthNumber) {
        for (String key : patients.keySet()) {
            if (key.equals(personalHealthNumber)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void save(PrintWriter printWriter) {
        for (String key : getPatientKeySet()) {
            printWriter.print(key);
            printWriter.print(Reader.DELIMITER);
            printWriter.print(getPatient(key).getFirstName());
            printWriter.print(Reader.DELIMITER);
            printWriter.print(getPatient(key).getLastName());
            printWriter.print(Reader.DELIMITER);
            saveList(printWriter, getPatient(key).getDiagnoses());
            saveList(printWriter, getPatient(key).getMedications());
        }
    }

    private void saveList(PrintWriter printWriter, ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            printWriter.print(list.get(i));
            printWriter.print(Reader.DELIMITER);
        }
    }
}