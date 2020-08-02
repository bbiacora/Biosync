package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

// Represents a writer that can write patient data to a file
// Reference: https://github.students.cs.ubc.ca/CPSC210/TellerApp
public class Writer {
    private PrintWriter printWriter;

    // EFFECTS: constructs a writer that will write data to file
    public Writer(File file) throws FileNotFoundException, UnsupportedEncodingException {
        printWriter = new PrintWriter(file, "UTF-8");
    }

    // MODIFIES: this
    // EFFECTS: writes savable to file
    public void write(Savable savable) {
        savable.save(printWriter);
    }

    // MODIFIES: this
    // EFFECTS: close print writer
    // NOTE: you MUST call this method when you are done writing data!
    public void close() {
        printWriter.close();
    }
}
