package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static task.ValidationUtilities.*;

public abstract class Task {
    private static int idGenerator;
    private String title;
    private TaskType type;
    private int id;
    private LocalDateTime dateTime;
    private String description;

    public Task(String title, String description, TaskType type, LocalDateTime dateTime) {

        this.title = checkTitleValidation(title);
        this.description = checkDescriptionValidation(description);
        this.type = type;
        this.dateTime = dateTime;
        this.id = ++idGenerator;
    }

    //region getters
    public String getTitle() {
        return title;
    }

    public TaskType getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getFirstDate() {
        return dateTime;
    }
    public LocalDate getLocalDate() {
        return dateTime.toLocalDate();
    }

    public String getDescription() {
        return description;
    }

    //endregion

    //region setters
    public void setTitle(String title){
        this.title = checkTitleValidation(title);
    }
    public void setDescription(String description){
        this.description = checkDescriptionValidation(description);
    }

    //endregion
    public abstract boolean appearsLn(LocalDateTime dateForChecking);

    @Override
    public String toString() {
        return "\nЗаголовок = " + title +
                ",\nОписание = " + description +
                "\nТип задачи = " + type.getType() +
                ",\nid = " + id +
                ",\nВремя создания = " + dateTime +
                "\n___________________________________\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                Objects.equals(title, task.title) &&
                type == task.type &&
                Objects.equals(dateTime, task.dateTime) &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type, id, dateTime, description);
    }
}
