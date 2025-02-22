package TaskTracker;

public final class Error {

    Error() {
    }

    public static void error(int num) {
        switch (num) {
            case 55:
                System.out.println("This task name already exist");
                return;
            case 56:
                System.out.println("This epic name already exist");
                return;
            case 57:
                System.out.println("This subtask name already exist");
                return;
            case 66:
                System.out.println("Name cannot be empty");
                return;
            case 97:
                System.out.println("Task ID not found");
                return;
            case 98:
                System.out.println("Epic ID not found");
                return;
            case 99:
                System.out.println("Subtask ID not found");
                return;
            case 103:
                System.out.println("Task name not found");
                return;
            case 104:
                System.out.println("Epic name not found");
                return;
            case 105:
                System.out.println("Subtask name not found");
        }
    }
}
