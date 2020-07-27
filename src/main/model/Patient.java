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
    // EFFECTS: adds a diagnosis to patient's list of diagnoses
    public void addDiagnosis(String diagnosis) {
        this.diagnoses.add(diagnosis);
    }

    // MODIFIES: this
    // EFFECTS: searches for a diagnosis in diagnoses and removes it if found
    public void removeDiagnosis(String diagnosis) {
        for (String d : diagnoses) {
            if (d.equalsIgnoreCase(diagnosis)) {
                diagnoses.remove(d);
            }
        }
    }

    // TODO: removeDiagnosis() method if there's time

    // MODIFIES: this
    // EFFECTS: adds a medication to patient's list of medications
    public void addMedication(String medication) {
        medications.add(medication);
    }

    // MODIFIES: this
    // EFFECTS: searches for a medication in medications and removes it if found
    public void removeMedication(String medication) {
        for (String m : medications) {
            if (m.equalsIgnoreCase(medication)) {
                medications.remove(m);
            }
        }
    }

    // EFFECTS: returns patient's personal health number
    public String getPersonalHealthNumber() {
        return personalHealthNumber;
    }

    // EFFECTS: returns patient's first name
    public String getFirstName() {
        return firstName;
    }

    // EFFECTS: returns patient's last name
    public String getLastName() {
        return lastName;
    }

    // EFFECTS: returns patient's a list of diagnoses
    public ArrayList<String> getDiagnoses() {
        return diagnoses;
    }

    // EFFECTS: returns patient's a list of medications
    public ArrayList<String> getMedications() {
        return medications;
    }
}
