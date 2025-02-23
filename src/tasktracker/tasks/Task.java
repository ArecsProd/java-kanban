package tasktracker.tasks;

import tasktracker.Status;

public class Task {
    protected String name; // public - т.к. мне нужен доступ к нему из TaskManager
    protected final int id;// protected - чтобы вне пакета задач вызывать только через гетер
    protected String description; // были default - потому что к ним не нужен был доступ вне пакета
    protected Status status = Status.NEW;

    public Task(String name, int id, String description) {
        this.name = name;
        this.id = id;
        this.description = description;
    }

    public Task(String name, int id, String description, Status status) {
        this(name, id, description);
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Task name - " + name + ", Task id - " + id + ", Task description - " + description + "\nStatus - "
                + status;
    }
}
