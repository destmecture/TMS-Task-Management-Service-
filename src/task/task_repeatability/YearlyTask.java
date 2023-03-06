package task.task_repeatability;

import task.Task;
import task.TaskType;

import java.time.LocalDateTime;

public class YearlyTask extends Task {
    public YearlyTask(String title, String description, TaskType type, LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsLn(LocalDateTime dateForChecking) {
        return getFirstDate().getDayOfYear()==dateForChecking.getDayOfYear();
    }

    @Override
    public String toString() {
        return "\n___________________________________"+
                "\nЕжегодная задача "+super.toString();
    }
}
