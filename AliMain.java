import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

class PlannedTask {

    int taskId;
    String title;
    String priority = "Medium";
    LocalDate deadline = null;
    String status = "Not Started";

    PlannedTask(int taskId, String title) {
        this.taskId = taskId;
        this.title = title;
    }
}

class PriorityDeadlineManager {

    ArrayList<PlannedTask> tasks = new ArrayList<>();

    void addTask(int id, String title) {

        tasks.add(new PlannedTask(id, title));
        System.out.println("Task added successfully!");
    }

    void setPriority(int id, String priority) {

        for (PlannedTask t : tasks) {

            if (t.taskId == id) {

                if (priority.equalsIgnoreCase("High") ||
                    priority.equalsIgnoreCase("Medium") ||
                    priority.equalsIgnoreCase("Low")) {

                    t.priority = priority;
                    System.out.println("Priority updated!");
                } else {
                    System.out.println("Invalid priority!");
                }

                return;
            }
        }

        System.out.println("Task not found!");
    }

    void setDeadline(int id, LocalDate date) {

        for (PlannedTask t : tasks) {

            if (t.taskId == id) {
                t.deadline = date;
                System.out.println("Deadline updated!");
                return;
            }
        }

        System.out.println("Task not found!");
    }

    void checkOverdueTasks() {

        boolean found = false;

        for (PlannedTask t : tasks) {

            if (t.deadline != null &&
                t.deadline.isBefore(LocalDate.now()) &&
                !t.status.equals("Completed")) {

                System.out.println("Overdue Task: " + t.title);
                System.out.println("Deadline: " + t.deadline);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No overdue tasks!");
        }
    }

    void displayTasks() {

        for (PlannedTask t : tasks) {

            System.out.println("Task ID: " + t.taskId);
            System.out.println("Title: " + t.title);
            System.out.println("Priority: " + t.priority);
            System.out.println("Deadline: " + t.deadline);
            System.out.println("Status: " + t.status);
            System.out.println("----------------");
        }
    }
}

public class AliMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PriorityDeadlineManager manager =
            new PriorityDeadlineManager();

        while (true) {

            System.out.println("\n--- ALI PRIORITY & DEADLINE ---");
            System.out.println("1. Add Task");
            System.out.println("2. Set Priority");
            System.out.println("3. Set Deadline");
            System.out.println("4. Check Overdue Tasks");
            System.out.println("5. View Tasks");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    System.out.print("Task ID: ");
                    int id = Integer.parseInt(sc.nextLine());

                    System.out.print("Task Title: ");
                    String title = sc.nextLine();

                    manager.addTask(id, title);
                    break;

                case 2:
                    System.out.print("Task ID: ");
                    id = Integer.parseInt(sc.nextLine());

                    System.out.print("Priority (High/Medium/Low): ");
                    String priority = sc.nextLine();

                    manager.setPriority(id, priority);
                    break;

                case 3:
                    System.out.print("Task ID: ");
                    id = Integer.parseInt(sc.nextLine());

                    try {
                        System.out.print("Deadline (YYYY-MM-DD): ");
                        LocalDate date =
                            LocalDate.parse(sc.nextLine());

                        manager.setDeadline(id, date);

                    } catch (Exception e) {
                        System.out.println("Invalid date!");
                    }
                    break;

                case 4:
                    manager.checkOverdueTasks();
                    break;

                case 5:
                    manager.displayTasks();
                    break;

                case 6:
                    System.out.println("Ali module completed!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}