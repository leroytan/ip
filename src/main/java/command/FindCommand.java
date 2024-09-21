package command;

import assertions.AssertCommand;
import components.Storage;
import components.TaskListHistory;
import components.Ui;
import exceptions.LightException;
import task.TaskList;

/**
 * Represents a command to find tasks in the task list.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a new FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) throws LightException {
        if (keyword == null || keyword.isEmpty()) {
            throw new LightException("The keyword cannot be empty.");
        }
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks in the task list.
     *
     * @param tasks           The task list to find tasks from.
     * @param ui              The user interface to display messages.
     * @param storage         The storage to save the task list to.
     * @param taskListHistory
     * @throws LightException If an error occurs while finding tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, TaskListHistory taskListHistory) throws LightException {
        new AssertCommand(tasks, ui, storage).assertExecute(tasks, ui, storage);
        TaskList foundTasks = tasks.findTasks(keyword);
        return ui.beautifyMessage(TaskList.arrayToNumberedString(foundTasks));
    }
}
