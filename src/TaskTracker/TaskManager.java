package TaskTracker;

import TaskTracker.Tasks.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {
    public static Map<Integer, Task> taskMap = new HashMap<>();
    public static Map<Integer, Epic> epicMap = new HashMap<>();
    public static Map<Integer, Subtask> subtaskMap = new HashMap<>();
    private static int id = 0;

    public int getTaskIdByName(String name) {
        for (Task task : taskMap.values()) {
            if (task.name.equals(name)) {
                return task.getId();
            }
        }
        return -1;
    }

    public void addTask(String name,  String description, Status status) {
        int id = generateId();
        taskMap.put(id, new Task(name, id, description, status));
    }

    public void updateTask(String name,  String description, Status status, Integer id) {

        taskMap.put(id, new Task(name, id, description, status));
    }

    public void updateTask(String name,  String description, Status status, String oldName) {
        int id = getTaskIdByName(oldName);
        taskMap.put(id, new Task(name, id, description, status));
    }

    public List<Task> getTaskList() {
        return new ArrayList<>(taskMap.values());
    }

    public String getTask(Task task) {
        return task.toString();
    }

    public void deleteTask(int id) {
        taskMap.remove(id);
    }

    public void deleteTask(String name) {
        int id = getTaskIdByName(name);
        taskMap.remove(id);
    }

    public int getEpicIdByName(String name) {
        for (Epic epic : epicMap.values()) {
            if (epic.name.equals(name)) {
                return epic.getId();
            }
        }
        return -1;
    }

    public void addEpic(String name, String description) {
        int id = generateId();
        epicMap.put(id, new Epic(name, id, description));
    }

    public void updateEpic(String name, String description, int id) {
        List<Integer> subtasks = epicMap.get(id).getSubtasks();
        List<Status> Status = epicMap.get(id).getStatus();
        epicMap.put(id, new Epic(name, id, description));
        epicMap.get(id).setSubtasks(subtasks);
        epicMap.get(id).setStatuses(Status);
    }

    public void updateEpic(String name, String description, String oldName) {
        int id = getEpicIdByName(oldName);
        updateEpic(name, description, id);
    }

    public List<Epic> getEpicList() {
        return new ArrayList<>(epicMap.values());
    }

    public String getEpic(Epic epic) {
        return epic.toString();
    }

    public void deleteEpic(int id) {
        while (!epicMap.get(id).getSubtasks().isEmpty()) {
            deleteSubtask(epicMap.get(id).getSubtasks().getFirst());
        }
        epicMap.remove(id);
    }

    public void deleteEpic(String name) {
        int id = getEpicIdByName(name);
        deleteEpic(id);
    }

    public int getSubtaskIdByName(String name) {
        for (Subtask subtask : subtaskMap.values()) {
            if (subtask.name.equals(name)) {
                return subtask.getId();
            }
        }
        return -1;
    }

    public void addSubtask(String name, String description, int epicId, Status status) {
        int id = generateId();
        subtaskMap.put(id, new Subtask(name, id, description, epicId, status));
        epicMap.get(epicId).setSubtask(id);
    }

    public void addSubtask(String name, String description, String epicName, Status status) {
        int epicId = getEpicIdByName(epicName);
        addSubtask(name, description, epicId, status);
    }

    public void updateSubtask(String name, String description, Status status, int id) {
        int epicId = subtaskMap.get(id).getEpicId();
        deleteSubtask(id);
        subtaskMap.put(id, new Subtask(name, id, description, epicId, status));
        epicMap.get(epicId).setSubtask(id);
    }

    public void updateSubtask(String name, String description, Status status, String oldName) {
        int id = getSubtaskIdByName(oldName);
        updateSubtask(name, description, status, id);
    }

    public String getSubtaskByEpic(int id) {
        if (epicMap.get(id).getSubtasks().isEmpty()) {
            return "Подзадач пока нет!";
        }
        return epicMap.get(id).subtasksToString();
    }

    public String getSubtaskByEpic(String epicName) {
        int id = getEpicIdByName(epicName);
        return getSubtaskByEpic(id);
    }

    public List<Subtask> getSubtasksList() {
        return new ArrayList<>(subtaskMap.values());
    }

    public void deleteSubtask(int id) {
        int epicId = subtaskMap.get(id).getEpicId();
        epicMap.get(epicId).deleteSubtask(id);
        subtaskMap.remove(id);
    }

    public String getSubtask(Subtask subtask) {
        return subtask.toString();
    }

    public void deleteSubtask(String name) {
        int id = getSubtaskIdByName(name);
        deleteSubtask(id);
    }

    private static int generateId() {
        id++;
        return id;
    }
}
