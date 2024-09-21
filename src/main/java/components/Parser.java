package components;

import command.*;
import exceptions.LightException;
import task.Deadline;
import task.Event;
import task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static command.Command.CommandType.*;

/**
 * Represents a parser that parses the user input and returns the corresponding command.
 */
public class Parser {

    /**
     * Parses the input string to a Command object.
     *
     * @param fullCommand The input string to be parsed.
     * @return The Command object parsed from the input string.
     * @throws LightException If the input string is invalid.
     */
    public static Command parse(String fullCommand) throws LightException {
        String[] splitedBySpace = fullCommand.split("\\s+", 2);
        String commandHeader = splitedBySpace[0];
        String commandDescription = splitedBySpace.length == 2 ? splitedBySpace[1] : null;
        return switch (getCommandType(commandHeader)) {
            case BYE -> new ExitCommand();
            case MARK -> new MarkCommand(commandDescription, true);
            case UNMARK -> new MarkCommand(commandDescription, false);
            case LIST -> new ListCommand();
            case DELETE -> new DeleteCommand(commandDescription);
            case FIND -> new FindCommand(commandDescription);
            case UNDO -> new UndoCommand();
            case REDO -> new RedoCommand();
            case HELP -> new HelpCommand();
            case TODO -> new AddCommand(new Todo(commandDescription));
            case DEADLINE -> new AddCommand(Deadline.createDeadline(commandDescription));
            case EVENT -> new AddCommand(Event.createEvent(commandDescription));
        };
    }

    /**
     * Parses the input string to a LocalDateTime object.
     *
     * @param input  The input string to be parsed.
     * @param format The format of the input string.
     * @return The LocalDateTime object parsed from the input string.
     */
    public static LocalDateTime dateTimeParser(String input, DateTimeFormatter format) {
        //Define the input format
        try {
            // Parse the input string to a LocalDateTime object
            // Return the LocalDateTime object
            return LocalDateTime.parse(input, format);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Please use the format: " + format.toString());
            return null;
        }
    }

    /**
     * Parses the input string to a CommandType object.
     *
     * @param Command The input string to be parsed.
     * @return The CommandType object parsed from the input string.
     * @throws LightException If the input string is invalid.
     */
    public static Command.CommandType getCommandType(String Command) throws LightException {
        switch (Command.toLowerCase()) {
        case "bye":
            return BYE;
        case "mark":
            return MARK;
        case "unmark":
            return UNMARK;
        case "list":
            return LIST;
        case "delete":
            return DELETE;
        case "find":
            return FIND;
        case "todo":
            return TODO;
        case "deadline":
            return DEADLINE;
        case "event":
            return EVENT;
        case "undo":
            return UNDO;
        case "redo":
            return REDO;
        case "help":
            return HELP;
        default:
            throw new LightException("Invalid command type");
        }
    }

    /**
     * Parses the input string to a CommandType object.
     *
     * @param Command The input string to be parsed.
     * @return The CommandType object parsed from the input string.
     * @throws LightException If the input string is invalid.
     */
    public static Command.CommandType getCommandTypeFromStorage(char Command) throws LightException {
        switch (Command) {
        case 'T':
            return TODO;
        case 'D':
            return DEADLINE;
        case 'E':
            return EVENT;
        default:
            throw new LightException("Invalid command type");
        }
    }
}
