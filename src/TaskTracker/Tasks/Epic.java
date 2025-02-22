package TaskTracker.Tasks;

import TaskTracker.Status;
import TaskTracker.TaskManager;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Integer> subtasks = new ArrayList<>();
    private List<Status> statuses = new ArrayList<>();

    public Epic(String name, int id, String description) {
        super(name, id, description);
        status = Status.NEW;
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
        if (subtasks.isEmpty()) {
            status = Status.NEW;
            return;
        }
        status = TaskManager.subtaskMap.get(subtasks.getFirst()).status;
        for (int subtaskId : subtasks) {
            switch (status) {
                case NEW:
                    if (TaskManager.subtaskMap.get(subtaskId).status != Status.NEW) {
                        status = Status.IN_PROGRESS;
                        return;
                    }
                    break;
                case DONE:
                    if (TaskManager.subtaskMap.get(subtaskId).status != Status.DONE) {
                        status = Status.IN_PROGRESS;
                        return;
                    }
                    break;
                default:
                    status = Status.IN_PROGRESS;
            }
        }
    }

    public List<Status> getStatus() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
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
                if (subtasks.contains(subtask.getId())) {
                    subtaskString.append("\n").append(subtask);
                }
            }
        }
        return subtaskString.toString();
    }
}
