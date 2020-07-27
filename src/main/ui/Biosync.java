package ui;

import model.Patients;

import java.util.ArrayList;
import java.util.Scanner;

// Patient management application
public class Biosync {
    private Scanner input;
    private Patients patients = new Patients();

    public Biosync() {
        System.out.println("WELCOME TO BIOSYNC!");
        runBiosync();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // Reference: https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void runBiosync() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processDisplayMenu(command);
            }
        }
    }

    // EFFECTS: displays main menu options to user
    // Reference: https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void displayMenu() {
        System.out.println("Select:");
        System.out.println("\tv ➝ View the registered patients");
        System.out.println("\ta ➝ Add a patient to the system");
        System.out.println("\tr ➝ Remove a patient from the system");
        System.out.println("\tq ➝ Quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    // Reference: https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void processDisplayMenu(String command) {
        if (command.equals("v")) {
            viewPatients();
        } else if (command.equals("a")) {
            addPatient();
        } else if (command.equals("r")) {
            removePatient();
        } else {
            System.err.println("Invalid input. Please try again.\n");
        }
    }

    // EFFECTS: prints name of all patient in patients along with their corresponding personal health number
    private void viewPatients() {
        if (patients.getPatientKeySet().size() == 0) {
            System.err.println("There are no patients registered in the system.\n");
        } else {
            System.out.println("PERSONAL HEALTH NUMBER\t" + " | " + "   \tNAME   ");
            for (String key : patients.getPatientKeySet()) {
                String personalHealthNumber = patients.getPatient(key).getPersonalHealthNumber();
                String firstName = patients.getPatient(key).getFirstName();
                String lastName = patients.getPatient(key).getLastName();
                System.out.println("\t\t" + personalHealthNumber + "\t\t\t" + " | \t " + lastName + ", " + firstName);
            }
            selectPatient();
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter patient's personal health number and first and last name;
    //          adds patient to patients if no patient is mapped to personal health number in patients;
    //          prints a confirmation that the patient has been added;
    private void addPatient() {
        System.out.println("Patient's 5-digit Personal Health Number:");
        String personalHealthNumber = input.nextLine();
        personalHealthNumber = validate(personalHealthNumber);

        if (patients.containsPatient(personalHealthNumber)) {
            System.err.println("Patient already resgistered in the system.");
        } else {
            System.out.println("Patient's first name: ");
            String firstName = input.nextLine();

            System.out.println("Patient's Last name: ");
            String lastName = input.nextLine();

            patients.addPatient(personalHealthNumber, firstName, lastName);
            System.out.println("\n" + firstName + " " + lastName + " has been added to the system.\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user for personal health number, if valid and
    //          if patient exisits, removes corresponding patient,
    //          otherwise prompts user to enter a new personal health number
    private void removePatient() {
        if (patients.getPatientKeySet().size() == 0) {
            System.err.println("There are no patients registered in the system.\n");
        } else {
            System.out.println("Enter patient's Personal Health Number");
            String personalHealthNumber = input.nextLine();
            personalHealthNumber = validate(personalHealthNumber);
            if (!patients.containsPatient(personalHealthNumber)) {
                System.err.println("Patient not found.\n");
                removePatient();
            } else {
                if (confirmRemoval()) {
                    System.out.println("\n" + patients.getPatient(personalHealthNumber).getFirstName() + " " + patients
                            .getPatient(personalHealthNumber).getLastName() + " has been removed from the system.\n");
                    patients.removePatient(personalHealthNumber);
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to select a patient;
    //          if patient exists, prints patient records;
    //          prompts user to modify patient's records
    private void selectPatient() {
        System.out.println("\nEnter patient's Personal Health Number to view their records");
        String personalHealthNumber = input.nextLine();
        personalHealthNumber = validate(personalHealthNumber);

        if (!patients.containsPatient(personalHealthNumber)) {
            System.out.println("Patient not found.\n");
        } else {
            printPatientRecord(personalHealthNumber);
            modifyPatientRecord(personalHealthNumber);
        }
    }

    // EFFECTS: displays patient menu options to user
    // Reference: https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void displayPatientMenu() {
        System.out.println("Select:");
        System.out.println("\td ➝ Add a diagnosis to patient's records");
//        System.out.println("\tv ➝ Remove a diagnosis to patient's records");
        System.out.println("\tm ➝ Add a medication to patient's records");
//        System.out.println("\tx ➝ Remove a medication to patient's records");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    // Reference: https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void processPatientMenu(String command, String personalHealthNumber) {
        if (command.equals("d")) {
            System.out.println("Enter diagnosis: ");
            command = input.nextLine();
            patients.getPatient(personalHealthNumber).addDiagnosis(command);
//        } else if (command.equals("v")) {
//            System.out.println("Enter diagnosis to be removed: ");
//            command = input.nextLine();
//            patients.getPatient(personalHealthNumber).removeDiagnosis(command);
        } else {
            System.out.println("Enter medication: ");
            command = input.nextLine();
            patients.getPatient(personalHealthNumber).addMedication(command);
//        } else {
//            System.out.println("Enter medication to be removed: ");
//            String response = input.nextLine();
//            patients.getPatient(personalHealthNumber).removeDiagnosis(response);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a diagnosis or a medication to patient's records
    private void modifyPatientRecord(String personalHealthNumber) {
        String command = "";

        while (!(command.equals("d") || command.equals("m"))) {
            displayPatientMenu();
            command = input.nextLine();
        }
        processPatientMenu(command, personalHealthNumber);
    }

    // EFFECTS: prints patient records
    private void printPatientRecord(String personalHealthNumber) {
        System.out.println("Personal Health Number: " + personalHealthNumber);
        System.out.println("Name: " + patients.getPatient(personalHealthNumber)
                .getFirstName() + patients.getPatient(personalHealthNumber).getLastName());
        System.out.println("Diagnoses: ");
        formatRecords(patients.getPatient(personalHealthNumber).getDiagnoses());
        System.out.println("\nMedications: ");
        formatRecords(patients.getPatient(personalHealthNumber).getMedications());
    }

    // EFFECTS: formats strings from a list for user readability and prints it
    private void formatRecords(ArrayList<String> list) {
        if (list == null) {
            System.out.println("-");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(" - " + list.get(i));
            }
        }
    }

    // EFFECTS: keeps prompting user for valid personal health number (length = 5 and numeric)
    //          returns a valid personal health number
    private String validate(String personalHealthNumber) {
        boolean keepGoing = true;

        while (keepGoing) {
            if (!(personalHealthNumber.length() == 5 && personalHealthNumber.matches("^[0-9]*$"))) {
                System.err.println("Please enter a valid personal health number.");
                personalHealthNumber = input.nextLine();
            } else {
                keepGoing = false;
            }
        }
        return personalHealthNumber;
    }

    // EFFECTS: prompts user for confirmation response ("y" or "n")
    //          returns true if user input = "y" and false if user input = "n"
    private boolean confirmRemoval() {
        System.err.println("Are you sure? You will lose all of the patient's information!");
        System.err.println("Enter 'y' to confirm or 'n' to cancel");
        String response = input.nextLine().toLowerCase();

        if (!(response.equals("y") || response.equals("n"))) {
            System.err.println("Invalid input. Please try again.\n");
            return false;
        } else if (response.equals("y")) {
            return true;
        } else {
            return false;
        }
    }
}


