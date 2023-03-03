package task;

public enum TaskType {
    WORK("Рабочая задача"), PERSONAL("Личная задача");
    private String type;
    TaskType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
