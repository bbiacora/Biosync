package model;

import java.util.HashMap;

// Represents a map of patients to be handled by Biosync
public class Patients {
    private HashMap<String, Patient> patients;

    //EFFECTS: initializes patients as an empty hashmap
    public Patients() {
        patients = new HashMap<>();
    }

    // MODIFIES: this
    // TODO EFFECTS: creates a new patient and adds it to patients if personal health number is valid
    public boolean addPatient(String personalHealthNumber, String firstName, String lastName) {
        if (!(this.containsPatient(personalHealthNumber))) {
            Patient patient = new Patient(personalHealthNumber, firstName, lastName);
            patients.put(personalHealthNumber, patient);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: patients is not null
    // MODIFIES: this
    // TODO EFFECTS: removes a patient in patients with the corresponding personal health number
    public boolean removePatient(String personalHealthNumber) {
        if (containsPatient(personalHealthNumber)) {
            patients.remove(personalHealthNumber);
            return true;
        }
        return false;
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
}