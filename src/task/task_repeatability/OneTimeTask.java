package task.task_repeatability;

import task.Task;
import task.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneTimeTask extends Task {
    public OneTimeTask(String title, String description, TaskType type, LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsLn(LocalDate dateForChecking) {
        return (dateForChecking.isEqual(getLocalDateTime().toLocalDate()));

    }

    @Override
    public String toString() {
        return "\n___________________________________"+
                "\nОднократная задача "+super.toString();
    }
}
