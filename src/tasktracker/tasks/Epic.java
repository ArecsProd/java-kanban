package tasktracker.tasks;

import tasktracker.Status;
import tasktracker.TaskManager;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Integer> subtasks = new ArrayList<>();

    public Epic(String name, int id, String description) {
        super(name, id, description);
        status = Status.NEW;
    }

    public void setSubtask(Integer id) {
        subtasks.add(id);
        updateStatus();
    }

    public void deleteSubtask(Integer id) {
        subtasks.remove(id);
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
        status = TaskManager.getSubtaskMap().get(subtasks.getFirst()).status;
        for (int subtaskId : subtasks) {
            switch (status) {
                case NEW:
                    if (TaskManager.getSubtaskMap().get(subtaskId).status != Status.NEW) {
                        status = Status.IN_PROGRESS;
                        return;
                    }
                    break;
                case DONE:
                    if (TaskManager.getSubtaskMap().get(subtaskId).status != Status.DONE) {
                        status = Status.IN_PROGRESS;
                        return;
                    }
                    break;
                default:
                    status = Status.IN_PROGRESS;
            }
        }
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
            for (Subtask subtask : TaskManager.getSubtaskMap().values()) {
                if (subtasks.contains(subtask.getId())) {
                    subtaskString.append("\n").append(subtask);
                }
            }
        }
        return subtaskString.toString();
    }
}
