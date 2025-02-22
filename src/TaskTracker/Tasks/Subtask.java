package TaskTracker.Tasks;

import TaskTracker.Statuses;

public class Subtask extends Epic {
    int epicId;
    Statuses status;

    public Subtask(String name, int id, String description, int epicId, Statuses status) {
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
