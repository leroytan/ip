package task;

import components.Parser;

import exceptions.LightException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) throws LightException {
        super(description);
        try {
            this.by = Parser.dateTimeParser(by, DateTimeFormatter.ofPattern("[d/MM/yyyy HHmm][MMM d yyyy HHmm]"));
            if (this.by == null) {
                throw new LightException("Please enter a valid date and time in the format: DD/MM/YYYY HHmm or MMM d yyyy HHmm");
            }
        } catch (DateTimeParseException e) {
            throw new LightException("Please enter a valid date and time in the format: DD/MM/YYYY HHmm or MMM d yyyy HHmm");
        }
    }

    public Deadline(String description, LocalDateTime by) throws LightException {
        super(description);
        try {
            this.by = by;
            if (this.by == null) {
                throw new LightException("Please enter a valid date and time in the format: DD/MM/YYYY HHmm or MMM d yyyy HHmm");
            }
        } catch (DateTimeParseException e) {
            throw new LightException("Please enter a valid date and time in the format: DD/MM/YYYY HHmm or MMM d yyyy HHmm");
        }
    }

    public static Deadline createDeadline(String commandDescription) throws LightException {
        try {
            String[] splitedBySlash = commandDescription.split("/by ");
            Deadline task = new Deadline(splitedBySlash[0], splitedBySlash[1].stripTrailing());
            return task;
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new LightException("The deadline command is invalid.");
        }
    }

    @Override
    public Task clone() {
        Task newTask = null;
        try {
            newTask = new Deadline(this.description, this.by);
        } catch (LightException e) {
            throw new RuntimeException(e);
        }
        newTask.isDone = this.isDone;
        return newTask;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}