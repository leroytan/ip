package task;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    public TaskList(ArrayList<Task> taskList) {
        super();
        for (int i = 0; i < taskList.size(); i++) {
            this.add(taskList.get(i).clone());
        }
    }

    public static <T> String arrayToNumberedString(ArrayList<T> array) {
        String taskString = IntStream.range(0, array.size()).mapToObj(number -> number + 1 + ". " + array.get(number)).map(Objects::toString)
                .collect(Collectors.joining("\n"));
        if (taskString.isEmpty()) {
            return "No tasks in the list.";
        }
        return taskString;
    }

    /**
     * Finds tasks that contain the given keyword.
     *
     * @param keyword The keyword to search for in the tasks.
     * @return A TaskList containing tasks that match the keyword.
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : this) {
            if (task.toString().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return new TaskList(foundTasks);
    }

    @Override
    public TaskList clone() {
        return new TaskList(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TaskList) {
            TaskList other = (TaskList) o;
            if (this.size() != other.size()) {
                return false;
            }
            for (int i = 0; i < this.size(); i++) {
                if (!this.get(i).equals(other.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
