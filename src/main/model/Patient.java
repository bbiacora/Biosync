package model;

import persistence.Reader;

import java.io.PrintWriter;
import java.util.ArrayList;

// Represents a patient with a personal health number, a first name, a last name,
// a list of diagnoses, and a list of medications
public class Patient extends Patients {
    private String personalHealthNumber;
    private String firstName;
    private String lastName;
    private ArrayList<String> diagnoses;
    private ArrayList<String> medications;

    // EFFECTS: sets patient's personal health number, first name and last name;
    //          initializes two empty lists for patient's diagnoses and medications
    public Patient(String personalHealthNumber, String firstName, String lastName) {
        this.personalHealthNumber = personalHealthNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.diagnoses = new ArrayList<>();
        this.medications = new ArrayList<>();
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

    // REQUIRES: diagnoses size <= 3
    // MODIFIES: this
    // EFFECTS: adds a diagnosis to patient's list of diagnoses
    public void addDiagnosis(String diagnosis) {
        for (int i = 0; i < 3; i++) {
            if (diagnoses.get(i).equals("-")) {
                diagnoses.set(i, diagnosis);
                break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: searches for a diagnosis in diagnoses and removes it if found
    public void removeDiagnosis(String diagnosis) {
        for (int i = 0; i < 3; i++) {
            if (medications.get(i).equals("-")) {
                medications.set(i, diagnosis);
                break;
            }
        }
    }

    // REQUIRES: medications size <= 3
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

    // MODIFIES: printWriter
    // EFFECTS: writes the savable to printWriter
    public void save(PrintWriter printWriter) {
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
    }

    // MODIFIES: printWriter
    // EFFECTS: writes the savable to printWriter
    protected void saveList(PrintWriter printWriter, ArrayList<String> list) {
        while (list.size() != 3) {
            list.add("-");
        }
        printWriter.print(list.get(0));
        printWriter.print(Reader.DELIMITER);
        printWriter.print(list.get(1));
        printWriter.print(Reader.DELIMITER);
        printWriter.print(list.get(2));
    }
}
