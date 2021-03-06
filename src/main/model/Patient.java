package model;

import persistence.Reader;
import persistence.Savable;

import java.io.PrintWriter;
import java.util.ArrayList;

// Represents a patient with a personal health number, a first name, a last name,
// a list of diagnoses, and a list of medications
public class Patient implements Savable {
    private String personalHealthNumber;
    private String firstName;
    private String lastName;
    private ArrayList<String> diagnoses;
    private ArrayList<String> medications;

    // EFFECTS: sets patient's personal health number, first name and last name;
    //          initializes two lists for patient's diagnoses and medications
    public Patient(String personalHealthNumber, String firstName, String lastName) {
        this.personalHealthNumber = personalHealthNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.diagnoses = new ArrayList<>();
        this.medications = new ArrayList<>();
        maintainList(this.diagnoses);
        maintainList(this.medications);
    }

    // EFFECTS: sets patient's personal health number, first name and last name;
    //          initializes two empty lists for patient's diagnoses and medications
    // NOTE: this constructor is to be used only when constructing a patient from data stored in file
    public Patient(String personalHealthNumber, String firstName, String lastName,
                   ArrayList<String> diagnoses, ArrayList<String> medications) {
        this.personalHealthNumber = personalHealthNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.diagnoses = diagnoses;
        this.medications = medications;
    }

    // MODIFIES: list
    // EFFECTS: adds "-" placeholder to list
    // NOTE: this is necessary, so that the list can be treated as a fixed list
    public void maintainList(ArrayList<String> list) {
        while (list.size() != 3) {
            list.add("-");
        }
    }

    // REQUIRES: diagnoses size = 3
    // MODIFIES: this
    // EFFECTS: if there is room left in the list (i.e. contains "-" placeholder),
    //          adds diagnosis to diagnoses by replacing the placeholder with it;
    //          stores elements in diagnoses in all UPPERCASE string
    public void addDiagnosis(String diagnosis) {
        diagnosis = diagnosis.toUpperCase();
        for (int i = 0; i < 3; i++) {
            if (diagnoses.get(i).equals("-")) {
                diagnoses.set(i, diagnosis);
                return;
            }
        }
    }

    // REQUIRES: diagnoses size = 3
    // MODIFIES: this
    // EFFECTS: if diagnosis is in diagnoses, removes diagnosis from diagnoses
    //          and replaces it with a "-" placeholder
    public void removeDiagnosis(String diagnosis) {
        diagnosis = diagnosis.toUpperCase();
        if (diagnoses.contains(diagnosis)) {
            diagnoses.remove(diagnosis);
            diagnoses.add("-");
        }
    }

    // REQUIRES: medications size = 3
    // MODIFIES: this
    // EFFECTS: if there is room left in the list (i.e. contains "-" placeholder),
    //          adds medication to medications by replacing the placeholder with it;
    //          stores elements in medications in all UPPERCASE string
    public void addMedication(String medication) {
        medication = medication.toUpperCase();
        for (int i = 0; i < 3; i++) {
            if (medications.get(i).equals("-")) {
                medications.set(i, medication);
                break;
            }
        }
    }

    // REQUIRES: medications size = 3
    // MODIFIES: this
    // EFFECTS: if medication is in medications, removes medication from medications
    //          and replaces it with a "-" placeholder
    public void removeMedication(String medication) {
        medication = medication.toUpperCase();
        if (medications.contains(medication)) {
            medications.remove(medication);
            medications.add("-");
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

    // EFFECTS: returns patient's list of diagnoses
    public ArrayList<String> getDiagnoses() {
        return diagnoses;
    }

    // EFFECTS: returns patient's list of medications
    public ArrayList<String> getMedications() {
        return medications;
    }

    // MODIFIES: printWriter
    // EFFECTS: writes the savable to printWriter, returns true at the end of the execution
    // Reference: https://github.students.cs.ubc.ca/CPSC210/TellerApp
    public boolean save(PrintWriter printWriter) {
        printWriter.print(personalHealthNumber);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(firstName);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(lastName);
        printWriter.print(Reader.DELIMITER);
        saveList(printWriter, this.getDiagnoses());
        printWriter.print(Reader.DELIMITER);
        saveList(printWriter, this.getMedications());
        printWriter.println();
        return true;
    }

    // MODIFIES: list, printWriter
    // EFFECTS: writes list to printWriter, returns true at the end of the execution
    // Reference: https://github.students.cs.ubc.ca/CPSC210/TellerApp
    public boolean saveList(PrintWriter printWriter, ArrayList<String> list) {
        maintainList(list);
        printWriter.print(list.get(0));
        printWriter.print(Reader.DELIMITER);
        printWriter.print(list.get(1));
        printWriter.print(Reader.DELIMITER);
        printWriter.print(list.get(2));
        return true;
    }

}
