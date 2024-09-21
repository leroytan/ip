package components;

import command.Command;
import exceptions.LightException;
import javafx.application.Platform;
import task.TaskList;

/**
 * Represents the main class of the components.Light program.
 */
public class Light {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final TaskListHistory taskListHistory;

    /**
     * Creates a components.Light object.
     *
     * @param filePath The file path of the storage file.
     */
    public Light(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
        this.taskListHistory = new TaskListHistory();
        this.taskListHistory.add(tasks.clone());
    }

    /**
     * Gets the response from the Light program.
     *
     * @param input The input to the Light program.
     * @return The response from the Light program.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                Platform.exit();
            }
            return c.execute(tasks, ui, storage, taskListHistory);
        } catch (LightException e) {
            return e.toString();
        }
    }
}

