package TaskTracker.Tasks;

import TaskTracker.Status;

public class Subtask extends Task {
    int epicId;
    Status status;

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
