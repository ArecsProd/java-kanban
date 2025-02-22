package TaskTracker.Tasks;

import TaskTracker.Statuses;

public class Task {
    public String name;
    protected final int id;
    String description;
    Statuses status = Statuses.NEW;

    public Task(String name, int id, String description) {
        this.name = name;
        this.id = id;
        this.description = description;
    }

    public Task(String name, int id, String description, Statuses status) {
        this(name, id, description);
        this.status = status;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Task name - " + name + ", Task id - " + id + ", Task description - " + description + "\nStatus - "
                + status;
    }
}
