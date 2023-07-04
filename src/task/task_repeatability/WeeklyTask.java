package task.task_repeatability;

import task.Task;
import task.TaskType;

import java.time.LocalDateTime;

public class WeeklyTask extends Task {
    public WeeklyTask(String title, String description, TaskType type, LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsLn(LocalDateTime dateForChecking) {
        return getFirstDate().getDayOfWeek()==dateForChecking.getDayOfWeek();
    }

    @Override
    public String toString() {
        return "\n___________________________________"+
                "\nЕженедельная задача "+super.toString();
    }
}
