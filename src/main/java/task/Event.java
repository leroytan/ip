package task;

import components.Parser;
import exceptions.LightException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;


    public Event(String description, String start, String end) throws LightException {
        super(description);
        this.start = Parser.dateTimeParser(start, DateTimeFormatter.ofPattern("[d/MM/yyyy HHmm][MMM d yyyy HHmm]"));
        this.end = Parser.dateTimeParser(end, DateTimeFormatter.ofPattern("[d/MM/yyyy HHmm][MMM d yyyy HHmm]"));
        if (this.start.isAfter(this.end)) {
            throw new LightException("The start time of the event cannot be after the end time.");
        }
    }

    public Event(String description, LocalDateTime start, LocalDateTime end) throws LightException {
        super(description);
        this.start = start;
        this.end = end;
        if (this.start.isAfter(this.end)) {
            throw new LightException("The start time of the event cannot be after the end time.");
        }
    }

    public static Event createEvent(String commandDescription) throws LightException {
        try {
            String[] splitedBySlash = commandDescription.split("/from ");
            String[] splitedBySlashTo = commandDescription.split("/to ");
            Event task = new Event(splitedBySlash[0], splitedBySlash[1].substring(0, splitedBySlash[1].indexOf("/to ")).stripTrailing(), splitedBySlashTo[1]);
            return task;
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new LightException("The event command is invalid.");
        }
    }

    @Override
    public Task clone() {
        Task newTask = null;
        try {
            newTask = new Event(this.description, this.start, this.end);
        } catch (LightException e) {
            throw new RuntimeException(e);
        }
        newTask.isDone = this.isDone;
        return newTask;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}