package model;

import java.util.ArrayList;

// Represents a patient with a personal health number, a first name, a last name,
// a list of diagnoses, and a list of medications
public class Patient {
    private String personalHealthNumber;
    private String firstName;
    private String lastName;
    private ArrayList<String> diagnoses;
    private ArrayList<String> medications;

    // REQUIRES: personal health number length = 10
    // EFFECTS: sets patients's personal health number, first name and last name;
    //          initializes two empty lists for patient's diagnoses and medications
    public Patient(String personalHealthNumber, String firstName, String lastName) {
        this.personalHealthNumber = personalHealthNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.diagnoses = new ArrayList<>();
        this.medications = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a diagnosis to a list of diagnoses
    public void addDiagnosis(String diagnosis) {
        diagnoses.add(diagnosis);
    }

    // MODIFIES: this
    // EFFECTS: adds a medication to a list of medications
    public void addMedication(String medication) {
        medications.add(medication);
    }

    // REQUIRES: medications is not null
    // MODIFIES: this
    // EFFECTS: searches for a medication in medications and removes it
    public void removeMedication(String medication) {
        for (String m : medications) {
            if (m.equalsIgnoreCase(medication)) {
                medications.remove(m);
            }
        }
    }

    // GETTERS
    public String getPersonalHealthNumber() {
        return personalHealthNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ArrayList<String> getDiagnoses() {
        return diagnoses;
    }

    public ArrayList<String> getMedications() {
        return medications;
    }
}
