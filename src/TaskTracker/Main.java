package TaskTracker;

import TaskTracker.Tasks.Epic;
import TaskTracker.Tasks.Subtask;
import TaskTracker.Tasks.Task;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");

        TaskManager taskManager = new TaskManager();
        System.out.println("\n\nСоздаём задачу \"Задача 1\"");
        taskManager.addTask("Задача 1", "Выполнить задачу 1", Status.NEW);
        System.out.println("Создаём задачу \"Задача 2\"");
        taskManager.addTask("Задача 2", "Выполнить задачу 2", Status.DONE);
        System.out.println("Создаём задачу \"Задача 3\"\n\n");
        taskManager.addTask("Задача 3", "Выполнить задачу 3", Status.IN_PROGRESS);

//        System.out.println("Пытаемся добавить подзадачу при отсутствии эпиков");
//        taskManager.addSubtask("Подзадача 1.3", "Это 1.3", "Эпик 1", Status.IN_PROGRESS);

        System.out.println("\n\nСоздаём эпик \"Эпик 1\"");
        taskManager.addEpic("Эпик 1", "Создать Эпик 1");
        System.out.println("Создаём эпик \"Эпик 2\"");
        taskManager.addEpic("Эпик 2", "Создать Эпик 2");
        System.out.println("Создаём эпик \"Эпик 3\"\n\n");
        taskManager.addEpic("Эпик 3", "Создать Эпик 3");

        System.out.println("Создаём подзадачу для Эпик 1 \"Подзадача 1.1\"");
        taskManager.addSubtask("Подзадача 1.1", "Это 1.1", "Эпик 1", Status.NEW);
        System.out.println("Создаём подзадачу для Эпик 1 \"Подзадача 1.2\"");
        taskManager.addSubtask("Подзадача 1.2", "Это 1.2", 4, Status.DONE);
        System.out.println("Создаём подзадачу для Эпик 1 \"Подзадача 1.3\"");
        taskManager.addSubtask("Подзадача 1.3", "Это 1.3", 4, Status.IN_PROGRESS);

        System.out.println("Создаём подзадачу для Эпик 2 \"Подзадача 2.1\"");
        taskManager.addSubtask("Подзадача 2.1", "Это 2.1", "Эпик 2", Status.DONE);
        System.out.println("Создаём подзадачу для Эпик 2 \"Подзадача 2.2\"");
        taskManager.addSubtask("Подзадача 2.2", "Это 2.2", "Эпик 2", Status.DONE);

        System.out.println("Создаём подзадачу для Эпик 3 \"Подзадача 3.1\"");
        taskManager.addSubtask("Подзадача 3.1", "Это 3.1", 6, Status.DONE);

        System.out.println("\nCписок задач - ");
        for (Task task : taskManager.getTaskList()) {
            System.out.println(taskManager.getTask(task));
        }
        System.out.println("\nCписок эпиков - ");
        for (Epic epic : taskManager.getEpicList()) {
            System.out.println(taskManager.getEpic(epic));
        }
        System.out.println("Cписок подзадач Эпика 1 - " + taskManager.getSubtaskByEpic("Эпик 1"));

        System.out.println("Cписок подзадач Эпика 3 - " + taskManager.getSubtaskByEpic(6));

        System.out.println("\nУдаляем задачу 2");
        taskManager.deleteTask("Задача 2");
        System.out.println("Cписок задач - ");
        for (Task task : taskManager.getTaskList()) {
            System.out.println(task.toString());
        }

        System.out.println("\nУдаляем задачу 3");
        taskManager.deleteTask(3);
        System.out.println("Cписок задач - ");
        for (Task task : taskManager.getTaskList()) {
            System.out.println(task.toString());
        }

        System.out.println("\nУдаляем подзадачу 3.1");
        taskManager.deleteSubtask("Подзадача 3.1");
        System.out.println("Cписок подзадач Эпика 3 - ");
        System.out.println(taskManager.getSubtaskByEpic(6));

        System.out.println("\nУдаляем подзадачу 1.1");
        taskManager.deleteSubtask(7);
        System.out.println("Cписок подзадач Эпика 1 - ");
        System.out.println(taskManager.getSubtaskByEpic("Эпик 1"));

        System.out.println("\nУдаляем эпик 2");
        taskManager.deleteEpic("Эпик 2");
        System.out.println("Cписок эпиков - ");
        for (Epic epic : taskManager.getEpicList()) {
            System.out.println(taskManager.getEpic(epic));
        }

        System.out.println("\nУдаляем эпик 3");
        taskManager.deleteEpic(6);
        System.out.println("Cписок эпиков - ");
        for (Epic epic : taskManager.getEpicList()) {
            System.out.println(taskManager.getEpic(epic));
        }

        System.out.println("\nОбновляем Задачу 1");
        taskManager.updateTask("Всё ещё задача 1", "Новое описание Задачи 1", Status.IN_PROGRESS,
                1);
        System.out.println("Cписок задач - ");
        for (Task task : taskManager.getTaskList()) {
            System.out.println(taskManager.getTask(task));
        }

        System.out.println("\nОбновляем Задачу 1 ещё раз!");
        taskManager.updateTask("Задача 1 - *3", "Новейшее описание Задачи 1", Status.DONE,
                "Всё ещё задача 1");
        System.out.println("Cписок задач - ");
        for (Task task : taskManager.getTaskList()) {
            System.out.println(taskManager.getTask(task));
        }

        System.out.println("\nОбновляем Эпик 1");
        taskManager.updateEpic("Новый эпик 1", "Новое описание эпика 1", 4);
        System.out.println("Cписок эпиков - ");
        for (Epic epic : taskManager.getEpicList()) {
            System.out.println(taskManager.getEpic(epic));
        }

        System.out.println("\nОбновляем Эпик 1 ещё раз");
        taskManager.updateEpic("Суперновый эпик 1", "Суперновое описание эпика 1", "Новый эпик 1");
        System.out.println("Cписок эпиков - ");
        for (Epic epic : taskManager.getEpicList()) {
            System.out.println(taskManager.getEpic(epic));
        }

        System.out.println("\nОбновляем подзадачу 1.2");
        taskManager.updateSubtask("Подзадача 1.2.2", "Это вторая версия подзадачи 1.2", Status.DONE,
                8);
        System.out.println("Cписок подзадач Эпика 1 - " + taskManager.getSubtaskByEpic("Суперновый эпик 1"));

        System.out.println("\nОбновляем подзадачу 1.3");
        taskManager.updateSubtask("Подзадача 1.3.2", "Это вторая версия подзадачи 1.3", Status.DONE,
                "Подзадача 1.3");
        System.out.println("Cписок подзадач Эпика 1 - " + taskManager.getSubtaskByEpic(4));

//        System.out.println("\nTasks critical tests\n");
//        System.out.println("\nTask list");
//        System.out.println("Cписок задач - ");
//        for (Task task : taskManager.getTaskList()) {
//            System.out.println(taskManager.getTask(task));
//        }
//        System.out.println("\nTask with empty name");
//        taskManager.addTask("", "", Status.NEW);
//        System.out.println("Already exist name");
//        taskManager.addTask("Задача 1 - *3", "", Status.DONE);
//        System.out.println("Delete by not found ID");
//        taskManager.deleteTask(2);
//        System.out.println("Update by not found name");
//        taskManager.updateTask("Task 1", " LoL", Status.IN_PROGRESS, "Task 1");
//        System.out.println("\nCписок задач - ");
//        for (Task task : taskManager.getTaskList()) {
//            System.out.println(taskManager.getTask(task));
//        }
//
//        System.out.println("\nEpics critical tests\n");
//        System.out.println("Epics list");
//        System.out.println("Cписок эпиков - ");
//        for (Epic epic : taskManager.getEpicList()) {
//            System.out.println(taskManager.getEpic(epic));
//        }
//        System.out.println("\nEpic with empty name");
//        taskManager.addEpic("", "He-he");
//        System.out.println("Update epic when oldName equals newName");
//        taskManager.updateEpic("Суперновый эпик 1", "Haha", "Суперновый эпик 1");
//        System.out.println("\nCписок эпиков - ");
//        for (Epic epic : taskManager.getEpicList()) {
//            System.out.println(taskManager.getEpic(epic));
//        }
//
//        System.out.println("\nSubtasks critical tests\n");
//        System.out.println("Subtasks list");
//        for (Subtask subtask : taskManager.getSubtasksList()) {
//            System.out.println(taskManager.getSubtask(subtask));
//        }
//        System.out.println("\nSubtask with not found epicID");
//        taskManager.addSubtask("Subtask 1", "new subtask", 1, Status.DONE);
    }
}
