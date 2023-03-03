package task.task_repeatability;

import task.Task;
import task.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task {
    public WeeklyTask(String title, String description, TaskType type, LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsLn(LocalDate dateForChecking) {
        return (dateForChecking.isAfter(getLocalDateTime().toLocalDate()) || dateForChecking.isEqual(getLocalDateTime().toLocalDate())
                && dateForChecking.getDayOfWeek() == getLocalDateTime().getDayOfWeek());
    }

    @Override
    public String toString() {
        return "\n___________________________________"+
                "\nЕженедельная задача "+super.toString();
    }
}
