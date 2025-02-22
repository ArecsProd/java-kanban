package TaskTracker;

import TaskTracker.Tasks.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static TaskTracker.Error.error;

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

    public void addTask(String name,  String description, Statuses status) {
        if (name.isEmpty()) {
            error(66);
            return;
        } else if (getTaskIdByName(name) != -1) {
            error(55);
            return;
        }
        int id = generateId();
        taskMap.put(id, new Task(name, id, description, status));
    }

    public void updateTask(String name,  String description, Statuses status, Integer id) {
        if (!taskMap.containsKey(id)) {
            error(97);
            return;
        } else if (name.isEmpty()) {
            error(66);
            return;
        } else if (!(getTaskIdByName(name) == id || getTaskIdByName(name) == -1)) {
            error(55);
            return;
        }
        taskMap.put(id, new Task(name, id, description, status));
    }

    public void updateTask(String name,  String description, Statuses status, String oldName) {
        int id = getTaskIdByName(oldName);
        if (id == -1) {
            error(103);
            return;
        }
        taskMap.put(id, new Task(name, id, description, status));
    }

    public List<Task> getTaskList() {
        return new ArrayList<>(taskMap.values());
    }

    public String getTask(Task task) {
        return task.toString();
    }

    public void deleteTask(int id) {
        if (!taskMap.containsKey(id)) {
            error(97);
            return;
        }
        taskMap.remove(id);
    }

    public void deleteTask(String name) {
        int id = getTaskIdByName(name);
        if (id == -1) {
            error(103);
            return;
        }
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
        if (name.isEmpty()) {
            error(66);
            return;
        } else if (getEpicIdByName(name) != -1) {
            error(56);
            return;
        }
        int id = generateId();
        epicMap.put(id, new Epic(name, id, description));
    }

    public void updateEpic(String name, String description, int id) {
        if (!epicMap.containsKey(id))  {
            error(98);
        } else if (name.isEmpty()) {
            error(66);
            return;
        } else if (!(getEpicIdByName(name) == -1 || getEpicIdByName(name) == id)) {
            error(56);
            return;
        }
        List<Integer> subtasks = epicMap.get(id).getSubtasks();
        List<Statuses> statuses = epicMap.get(id).getStatuses();
        epicMap.put(id, new Epic(name, id, description));
        epicMap.get(id).setSubtasks(subtasks);
        epicMap.get(id).setStatuses(statuses);
    }

    public void updateEpic(String name, String description, String oldName) {
        int id = getEpicIdByName(oldName);
        if (id == -1) {
            error(104);
            return;
        }
        updateEpic(name, description, id);
    }

    public List<Epic> getEpicList() {
        return new ArrayList<>(epicMap.values());
    }

    public String getEpic(Epic epic) {
        return epic.toString();
    }

    public void deleteEpic(int id) {
        if (!epicMap.containsKey(id)) {
            error(98);
            return;
        }
        while (!epicMap.get(id).getSubtasks().isEmpty()) {
            deleteSubtask(epicMap.get(id).getSubtasks().getFirst());
        }
        epicMap.remove(id);
    }

    public void deleteEpic(String name) {
        int id = getEpicIdByName(name);
        if (id == -1) {
            error(104);
            return;
        }
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

    public void addSubtask(String name, String description, int epicId, Statuses status) {
        if (epicMap.isEmpty() || !epicMap.containsKey(epicId)) {
            error(98);
            return;
        } else if (name.isEmpty()) {
            error(66);
            return;
        } else if (getSubtaskIdByName(name) != -1){
            error(57);
            return;
        }
        int id = generateId();
        subtaskMap.put(id, new Subtask(name, id, description, epicId, status));
        epicMap.get(epicId).setSubtask(id);
    }

    public void addSubtask(String name, String description, String epicName, Statuses status) {
        int epicId = getEpicIdByName(epicName);
        if (epicId == -1) {
            error(104);
        }
        addSubtask(name, description, epicId, status);
    }

    public void updateSubtask(String name, String description, Statuses status, int id) {
        if (!subtaskMap.containsKey(id)) {
            error(99);
            return;
        } else if (name.isEmpty()) {
            error(66);
            return;
        } else if (!(getSubtaskIdByName(name) == -1 || getSubtaskIdByName(name) == id)) {
            error(57);
            return;
        }
        int epicId = subtaskMap.get(id).getEpicId();
        deleteSubtask(id);
        subtaskMap.put(id, new Subtask(name, id, description, epicId, status));
        epicMap.get(epicId).setSubtask(id);
    }

    public void updateSubtask(String name, String description, Statuses status, String oldName) {
        int id = getSubtaskIdByName(oldName);
        if (id == -1) {
            error(105);
            return;
        }
        updateSubtask(name, description, status, id);
    }

    public String getSubtaskByEpic(int id) {
        if (!epicMap.containsKey(id)) {
            error(98);
            return null;
        }
        if (epicMap.get(id).getSubtasks().isEmpty()) {
            return "Подзадач пока нет!";
        }
        return epicMap.get(id).subtasksToString();
    }

    public String getSubtaskByEpic(String epicName) {
        int id = getEpicIdByName(epicName);
        if (id == -1) {
            error(104);
            return null;
        }
        return getSubtaskByEpic(id);
    }

    public List<Subtask> getSubtasksList() {
        return new ArrayList<>(subtaskMap.values());
    }

    public void deleteSubtask(int id) {
        if (!subtaskMap.containsKey(id)) {
            error(99);
            return;
        }
        int epicId = subtaskMap.get(id).getEpicId();
        epicMap.get(epicId).deleteSubtask(id);
        subtaskMap.remove(id);
    }

    public String getSubtask(Subtask subtask) {
        return subtask.toString();
    }

    public void deleteSubtask(String name) {
        int id = getSubtaskIdByName(name);
        if (id == -1) {
            error(104);
            return;
        }
        deleteSubtask(id);
    }

    private static int generateId() {
        id++;
        return id;
    }
}
