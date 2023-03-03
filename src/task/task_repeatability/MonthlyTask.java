package task.task_repeatability;

import task.Task;
import task.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task {
    public MonthlyTask(String title, String description, TaskType type, LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsLn(LocalDate dateForChecking) {
        return (dateForChecking.isAfter(getLocalDateTime().toLocalDate()) || dateForChecking.isEqual(getLocalDateTime().toLocalDate())
                && dateForChecking.getDayOfMonth() == getLocalDateTime().getDayOfMonth());
    }

    @Override
    public String toString() {
        return "\n___________________________________"+
                "\nЕжемесячная задача "+super.toString();
    }
}
