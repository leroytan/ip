package assertions;

import components.Storage;
import components.Ui;
import task.Task;
import task.TaskList;

public class AssertCommand {
    private Task task;
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    public AssertCommand(Task task, TaskList taskList, Ui ui, Storage storage) {
        this.task = task;
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    public AssertCommand(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    public void assertExecute(Task task, TaskList taskList, Ui ui, Storage storage) {
        assert task != null : "Task should not be null";
        assert taskList != null : "Task list should not be null";
        assert ui != null : "UI should not be null";
        assert storage != null : "Storage should not be null";
    }

    public void assertExecute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null : "Task list should not be null";
        assert ui != null : "UI should not be null";
        assert storage != null : "Storage should not be null";
    }
}
