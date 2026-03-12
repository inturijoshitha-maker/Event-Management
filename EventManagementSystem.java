import java.util.*;

class Event {
    int id;
    String name;
    String date;
    Event next;

    Event(int id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.next = null;
    }
}

class EventList {

    Event head;

    void addEvent(int id, String name, String date) {

        Event newEvent = new Event(id, name, date);

        if (head == null) {
            head = newEvent;
        } else {
            Event temp = head;
            while (temp.next != null)
                temp = temp.next;

            temp.next = newEvent;
        }
    }

    void displayEvents() {

        if (head == null) {
            System.out.println("No Events Available");
            return;
        }

        Event temp = head;

        while (temp != null) {
            System.out.println(temp.id + " | " + temp.name + " | " + temp.date);
            temp = temp.next;
        }
    }

    Event searchEvent(int id) {

        Event temp = head;

        while (temp != null) {
            if (temp.id == id)
                return temp;

            temp = temp.next;
        }

        return null;
    }
}

public class EventManagementSystem {

    static HashMap<String, String> users = new HashMap<>();
    static EventList events = new EventList();

    static void register(Scanner sc) {

        System.out.print("Enter Username: ");
        String user = sc.next();

        System.out.print("Enter Password: ");
        String pass = sc.next();

        users.put(user, pass);

        System.out.println("Registration Successful!");
    }

    static boolean login(Scanner sc) {

        System.out.print("Enter Username: ");
        String user = sc.next();

        System.out.print("Enter Password: ");
        String pass = sc.next();

        if (users.containsKey(user) && users.get(user).equals(pass)) {
            System.out.println("Login Successful!");
            return true;
        }

        System.out.println("Invalid Login!");
        return false;
    }

    static void showMenu(Scanner sc) {

        while (true) {

            System.out.println("\n--- Event Menu ---");
            System.out.println("1. Show Present Events");
            System.out.println("2. Search Event");
            System.out.println("3. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\nAvailable Events:");
                    events.displayEvents();
                    break;

                case 2:
                    System.out.print("Enter Event ID: ");
                    int id = sc.nextInt();

                    Event e = events.searchEvent(id);

                    if (e != null)
                        System.out.println("Event Found: " + e.name + " on " + e.date);
                    else
                        System.out.println("Event Not Found");

                    break;

                case 3:
                    return;
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Sample Events
        events.addEvent(1, "Tech Fest", "25-Apr-2026");
        events.addEvent(2, "Coding Competition", "02-May-2026");
        events.addEvent(3, "Workshop on AI", "10-May-2026");

        while (true) {

            System.out.println("\n===== Event System =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    register(sc);
                    break;

                case 2:
                    if (login(sc))
                        showMenu(sc);
                    break;

                case 3:
                    System.exit(0);
            }
        }
    }
}