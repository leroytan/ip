package command;

import assertions.AssertCommand;
import components.Storage;
import components.TaskListHistory;
import components.Ui;
import exceptions.LightException;
import task.TaskList;

/**
 * Represents a command to mark a task as done or undone.
 */
public class MarkCommand extends Command {
    private int taskNumber;
    private boolean isMark;

    /**
     * Creates a MarkCommand object.
     *
     * @param taskNumber The number of the task to be marked.
     * @param isMark     True if the task is to be marked as done, false if it is to be marked as undone.
     */
    public MarkCommand(String taskNumber, boolean isMark) throws LightException {
        super();
        try {
            this.taskNumber = Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException e) {
            throw new LightException("The task number is invalid.");
        }
        this.isMark = isMark;
    }

    /**
     * Marks the task as done or undone and updates the storage file.
     *
     * @param tasks           The task list.
     * @param ui              The user interface.
     * @param storage         The storage.
     * @param taskListHistory
     * @throws LightException if an error occurs during execution
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, TaskListHistory taskListHistory) throws LightException {
        new AssertCommand(tasks, ui, storage).assertExecute(tasks, ui, storage);
        String reply;
        try {
            if (isMark) {
                tasks.get(taskNumber).markAsDone();
                taskListHistory.add(tasks.clone());
                reply = ui.beautifyMessage("5..4..3..2..1\nI've marked this task as done:\n" + tasks.get(taskNumber));

            } else {
                tasks.get(taskNumber).markAsUndone();
                taskListHistory.add(tasks.clone());
                reply = ui.beautifyMessage("5..4..3..2..1\nI've marked this task as undone:\n" + tasks.get(taskNumber));
            }


            storage.write(TaskList.arrayToNumberedString(tasks));
            return reply;
        } catch (IndexOutOfBoundsException e) {
            throw new LightException("The task number is out of range.");
        }
    }
}
