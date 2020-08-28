![BIO](https://media.github.students.cs.ubc.ca/user/9769/files/f9c50700-cd48-11ea-93b4-e8d320c9a598)

Biosync is a patient management application that allows medical professionals to efficiently view <br> 
and update patient medical records, as well as track patient progress. 

## User Stories

**PHASE 1**
- [x] As a user, I want to be able to **add a patient** to a map of patients
- [x] As a user, I want to be able to **remove a patient** from a map of patients
- [x] As a user, I want to be able to **view the map of patients** in the system
- [x] As a user, I want to be able to **select a patient** from a map of patients **and view their records**
- [x] As a user, I want to be able to **add a diagnosis** to a patient’s records
- [x] As a user, I want to be able to **remove a diagnosis** from a patient's records
- [x] As a user, I want to be able to **add a medication** to patient's records
- [x] As a user, I want to be able to **remove a medication** from a patient's records

**PHASE 2**
- [x] As a user, I want to be able to **save the map of patients** to file
- [x] As a user, I want to be able to **load the map of patients** from file when the program starts

## Instructions for Grader
- You can **add** a patient by filling out the text fields in the `Patient Registration' panel, 
  <br> clicking the 'Register' button, and then clicking the 'Update' button
- You can **remove** a patient by selecting (single click) a row on the table, clicking the 'Remove' button, 
  <br> and then clicking 'Ok' on the pop-up window  
- You can locate my **visual components** by:
    - running Main (or instead, BiosyncGUI if latest commit is downloaded)
    - double clicking a row on the table
- You can trigger my **audio component** by:
    - selecting (single click) a table row and clicking the 'Remove' button
    - entering an invalid input* in the patient registration panel then clicking the register button
- You can **save** the state of my application by clicking File > Save on the menu bar 
  <br> (The application does not automatically save changes)
- Data from file is automatically **loaded** to the application when Main is ran

<br>

\* Invalid 'Patient Registration' panel text field inputs:
- Personal health number input is non-numeric, its length is < 5, or it's already registered in the system
- First or last name input is an empty string


## Phase 4: Task 2
Chosen construct: "Make appropriate use of the Map interface somewhere in your code"

The `Patients` class of this project makes use of a HashMap to represent a collection of patients 
registered in the system.


## Phase 4: Task 3
*Note: Design changes were made during Phase 3*

**COUPLING**
- `SelectedPatientWindow` displays the medical information of a selected patient to the GUI. Initially, it was 
associated to both `Patients` and `Patient`, but it did not make sense for it be associated to `Patients` 
since it only handles a single patient's information. There was high coupling between these three classes, so 
I removed the method that dealt with extracting a single patient fom a collection of patients in 
`SelectedPatientWindow`. Instead of passing it in with a full collection of patients, I refactored it so 
that `ViewPatientsPanel` (the class where  `SelectedPatientWindow` is called) first extracts the selected patient, 
and then passes that single patient to `SelectedPatientWindow`.

    ![coupling_1](https://media.github.students.cs.ubc.ca/user/9769/files/2328e100-dcbe-11ea-9731-161cff4ad586)

**COHESION**
- Initially, there was a panel class which handled both the displaying of all registered patients and the registration 
of patients, resulting in poor cohesion. So, I refactored the original class and created two classes, 
`ViewPatientsPanel` and `RegisterPatientsPanel`, that handled each job separately.

- Both `ViewPatientsPanel` and `RegisterPatientsPanel`, display a popup window in response a user input, and
an alert sound is played when this happens. Both classes used to have a `playSound()` method, but to reduce 
duplication, I created separate `SoundPlayer` class which is then called by both classes to play the sound.