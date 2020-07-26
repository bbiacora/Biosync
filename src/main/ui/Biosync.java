package ui;

import model.Patients;

import java.util.Scanner;

public class Biosync {
    private Scanner input;
    private Patients patients = new Patients();

    public Biosync() {
        runBiosync();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBiosync() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Goodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("v")) {
            doViewPatients();
        } else if (command.equals("a")) {
            doAddPatient();
        } else if (command.equals("r")) {
            doRemovePatient();
        } else {
            System.err.print("Invalid input. Please try again.\n\n");
        }
    }

    // EFFECTS: displays menu options to user
    private void displayMenu() {
        System.out.println("Welcome to Biosync!");
        System.out.println("Select from:");
        System.out.println("\tv ➝ View the patients in the system");
        System.out.println("\ta ➝ Add a patient to the system");
        System.out.println("\tr ➝ Remove a patient from the system");
        System.out.println("\tq ➝ Quit");
    }

    // EFFECTS: prints name of all patient in patients along with their corresponding personal health number
    private void doViewPatients() {
        System.out.println("PERSONAL HEALTH NUMBER\t" + " | " + "   \tNAME   ");
        for (String key : patients.getPatientKeySet()) {
            String personalHealthNumber = patients.getPatient(key).getPersonalHealthNumber();
            String firstName = patients.getPatient(key).getFirstName();
            String lastName = patients.getPatient(key).getLastName();
            System.out.println("\t\t" + personalHealthNumber + "\t\t\t" + " | \t " + lastName + ", " + firstName);
        }
        System.out.println();
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter patient's personal health number, and first and last name;
    //          adds patient to patients;
    //          prints a confirmation that the patient has been added;
    private void doAddPatient() {
        input = new Scanner(System.in);
        System.out.println("5-digit Personal Health Number:");
        String personalHealthNumber = input.next();
        personalHealthNumber = isValid(personalHealthNumber);
        System.out.println("First name:");
        String firstName = input.next();
        System.out.println("Last name: ");
        String lastName = input.next();
        patients.addPatient(personalHealthNumber, firstName, lastName);
        System.out.println("\n" + firstName + " " + lastName + " has been added to the system.\n");
        runBiosync();
    }

    // MODIFIES: this
    // EFFECTS: if patients contains patients with the corresponding personal health number,
    //          remove that patient, otherwise prompts user to enter a new personal health number
    private void doRemovePatient() {
        input = new Scanner(System.in);
        System.out.println("Please enter the 5-digit Personal Health Number of the patient that you want to remove.");
        String personalHealthNumber = input.next();
        isValid(personalHealthNumber);
        if (!patients.containsPatient(personalHealthNumber)) {
            System.err.print("Patient not found.\n");
            doRemovePatient();
        } else {
            if (confirmRemoval()) {
                System.out.println("\n" + patients.getPatient(personalHealthNumber).getFirstName() + " " + patients
                        .getPatient(personalHealthNumber).getLastName() + " has been removed from the system.\n");
                patients.removePatient(personalHealthNumber);
            }
        }
        runBiosync();
    }

    // EFFECTS: keeps prompting user for valid personal health number (length = 5 and numeric)
    //          returns a valid personal health number
    private String isValid(String personalHealthNumber) {
        boolean keepGoing = true;
        input = new Scanner(System.in);

        while (keepGoing) {
            if (!(personalHealthNumber.length() == 5 && personalHealthNumber.matches("^[0-9]*$"))) {
                System.err.print("Please enter a valid personal health number.\n");
                personalHealthNumber = input.next();
            } else {
                keepGoing = false;
            }
        }
        return personalHealthNumber;
    }

    // EFFECTS: keeps prompting user for valid response ("y" or "n")
    //          returns true if user input = "y" and false if user input = "n"
    private boolean confirmRemoval() {
        input = new Scanner(System.in);
        System.err.print("Are you sure? You will lose all of the patient's information!\n");
        System.err.print("Enter 'y' to confirm or 'n' to cancel.\n");
        String response = input.next();
        if (!(response.equals("y") || response.equals("n"))) {
            System.err.print("Invalid input. Please try again.\n\n");
            return false;
        } else if (response.equals("y")) {
            return true;
        } else {
            return false;
        }
    }
}


