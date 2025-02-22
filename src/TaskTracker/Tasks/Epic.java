package TaskTracker.Tasks;

import TaskTracker.Statuses;
import TaskTracker.TaskManager;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Integer> subtasks = new ArrayList<>();
    private List<Statuses> statuses = new ArrayList<>();

    public Epic(String name, int id, String description) {
        super(name, id, description);
        status = Statuses.NEW;
    }

    public void setSubtask(Integer id) {
        subtasks.add(id);
        statuses.add(TaskManager.subtaskMap.get(id).status);
        updateStatus();
    }

    public void deleteSubtask(Integer id) {
        subtasks.remove(id);
        statuses.remove(TaskManager.subtaskMap.get(id).status);
        updateStatus();
    }

    public List<Integer> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Integer> subtasks) {
        this.subtasks = subtasks;
    }

    private void updateStatus() {
        if (statuses.isEmpty() || (!statuses.contains(Statuses.DONE) && !statuses.contains(Statuses.IN_PROGRESS))) {
            status = Statuses.NEW;
        } else if (!statuses.contains(Statuses.NEW) && !statuses.contains(Statuses.IN_PROGRESS)){
            status = Statuses.DONE;
        } else {
            status = Statuses.IN_PROGRESS;
        }
    }

    public List<Statuses> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Statuses> statuses) {
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Epic name - " + name + ", Epic id - " + id + ", Epic description - " + description + "\nStatus - "
                + status + subtasksToString() + "\n";
    }

    public String subtasksToString() {
        StringBuilder subtaskString = new StringBuilder();
        if (subtasks.isEmpty()) {
            return "\nПодзадач пока нет!";
        } else {
            for (Subtask subtask : TaskManager.subtaskMap.values()) {
                if (subtasks.contains(subtask.id)) {
                    subtaskString.append("\n").append(subtask);
                }
            }
        }
        return subtaskString.toString();
    }
}
