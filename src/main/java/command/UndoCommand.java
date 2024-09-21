package command;

import assertions.AssertCommand;
import components.Storage;
import components.TaskListHistory;
import components.Ui;
import exceptions.LightException;
import task.TaskList;

/**
 * Represents a command to undo the last command.
 */
public class UndoCommand extends Command {
    public UndoCommand() {
        super();
    }

    /**
     * Undoes the last command.
     *
     * @param tasks           The task list.
     * @param ui              The user interface.
     * @param storage         The storage.
     * @param taskListHistory The task list history.
     * @return The message to be displayed to the user.
     * @throws LightException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, TaskListHistory taskListHistory) throws LightException {
        new AssertCommand(tasks, ui, storage).assertExecute(tasks, ui, storage);
        TaskList oldTasks = taskListHistory.getPreviousState();
        tasks.clear();
        tasks.addAll(oldTasks);
        String stringOfTask = TaskList.arrayToNumberedString(tasks);
        storage.write(stringOfTask);
        return ui.beautifyMessage("5..4..3..2..1\nI've undone the last command.\n" + stringOfTask);
    }
}
