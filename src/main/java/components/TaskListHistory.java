package components;

import exceptions.LightException;
import task.TaskList;

import java.util.ArrayList;

/**
 * Represents the history of task lists.
 */
public class TaskListHistory extends ArrayList<TaskList> {
    private int currentPointer;

    /**
     * Creates a TaskListHistory object.
     */
    public TaskListHistory() {
        super();
        this.currentPointer = 0;
    }

    /**
     * Adds a task list to the history.
     *
     * @param taskList The task list to be added.
     * @return True if the task list is added successfully.
     */
    public boolean add(TaskList taskList) {
        while (this.size() > this.currentPointer + 1) {
            this.remove(this.size() - 1);
        }
        if (this.size() > this.currentPointer && taskList.equals(this.get(this.currentPointer))) {
            return false;
        }
        super.add(taskList);
        currentPointer = this.size() - 1;
        return true;
    }

    /**
     * Gets the previous state of the task list.
     *
     * @return The previous state of the task list.
     * @throws LightException if there is no more previous state to undo.
     */
    public TaskList getPreviousState() throws LightException {
        if (this.currentPointer > 0) {
            this.currentPointer--;
            return this.get(this.currentPointer);
        } else {
            throw new LightException("No more previous state to undo.");
        }
    }

    /**
     * Gets the next state of the task list.
     *
     * @return The next state of the task list.
     * @throws LightException if there is no more next state to redo.
     */
    public TaskList getNextState() throws LightException {
        if (this.currentPointer < this.size() - 1) {
            this.currentPointer++;
            return this.get(this.currentPointer);
        } else {
            throw new LightException("No more next state to redo.");
        }
    }

}
