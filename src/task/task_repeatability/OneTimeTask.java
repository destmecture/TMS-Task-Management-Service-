package task.task_repeatability;

import task.Task;
import task.TaskType;

import java.time.LocalDateTime;

public class OneTimeTask extends Task {
    public OneTimeTask(String title, String description, TaskType type, LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsLn(LocalDateTime dateForChecking) {
        return getFirstDate().toLocalDate().isEqual(dateForChecking.toLocalDate());

    }

    @Override
    public String toString() {
        return "\n___________________________________"+
                "\nОднократная задача "+super.toString();
    }
}
