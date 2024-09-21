package task;

import exceptions.LightException;

public class Todo extends Task {

    public Todo(String description) throws LightException {
        super(description);
    }

    @Override
    public Task clone() {
        Task newTask = null;
        try {
            newTask = new Todo(this.description);
        } catch (LightException e) {
            throw new RuntimeException(e);
        }
        newTask.isDone = this.isDone;
        return newTask;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}