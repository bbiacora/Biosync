package model;

import persistence.Savable;

import java.io.PrintWriter;
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
    // EFFECTS: constructs a new patient and adds it to patients
    public void addPatient(String personalHealthNumber, String firstName, String lastName) {
        Patient patient = new Patient(personalHealthNumber, firstName, lastName);
        patients.put(personalHealthNumber, patient);
    }

    // MODIFIES: this
    // EFFECT: adds an existing patient to patients
    // NOTE: this method is to be used only when adding a patient to patients from data stored in file
    public void addPatient(Patient patient) {
        patients.put(patient.getPersonalHealthNumber(), patient);
    }

    // MODIFIES: this
    // EFFECTS: removes a patient in patients with the corresponding personal health number
    public void removePatient(String personalHealthNumber) {
        patients.remove(personalHealthNumber);
    }

    // EFFECTS: returns a patient, if patient is in patients otherwise, returns null
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

    // MODIFIES: printWriter
    // EFFECTS: writes the savable to printWriter, , returns true at the end of the execution
    // Reference: https://github.students.cs.ubc.ca/CPSC210/TellerApp
    @Override
    public boolean save(PrintWriter printWriter) {
        for (String key : getPatientKeySet()) {
            getPatient(key).save(printWriter);
        }
        return true;
    }
}