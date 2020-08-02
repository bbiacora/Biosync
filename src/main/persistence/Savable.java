package persistence;

import java.io.PrintWriter;

// Represents data that can be saved to file
// Reference: https://github.students.cs.ubc.ca/CPSC210/TellerApp
public interface Savable {

    // MODIFIES: printWriter
    // EFFECTS: writes the savable to printWriter
    void save(PrintWriter printWriter);
}
