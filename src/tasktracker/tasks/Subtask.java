package tasktracker.tasks;

import tasktracker.Status;

public class Subtask extends Task {
    private final int epicId;

    public Subtask(String name, int id, String description, int epicId, Status status) {
        super(name, id, description);
        this.epicId = epicId;
        this.status = status;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString(){
        return "Subtask name - " + name + ", subtask id - " + id + ", Subtask description - " + description
                + "\nStatus - " + status;
    }
}
