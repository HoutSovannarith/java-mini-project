import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    static String[][] morning;
    static String[][] afternoon;
    static String[][] night;
    static String[] booking = new String[10];
    static int rows;
    static int cols;
    static int row = 0, col = 0;
    static int row1, col1;
    static String seats;
    static int id;
    static Scanner input = new Scanner(System.in);
    static String[] bookID;
    static int n = 0;
    private static int bookingCount = 0;

    private static void menu() {
        String menu = null;
        do {
            System.out.println("+---------------------------------------------------------------+");
            System.out.println("|                    [[APPLICATION MENU]]                       |");
            System.out.println("|                    <A> Booking                                |");
            System.out.println("|                    <B> Hall                                   |");
            System.out.println("|                    <C> Showtime                               |");
            System.out.println("|                    <D> History                                |");
            System.out.println("|                    <E> Reboot                                 |");
            System.out.println("|                    <F> Exit                                   |");
            System.out.println("+---------------------------------------------------------------+");
            menu = validate("Please select menu: ", input, "[a-zA-Z]+");
            switch (menu) {
                case "a":
                    booking();
                    break;
                case "b":
                    System.out.println("+---------------------------------------------------------------+");
                    System.out.println("|                         Morning                               |");
                    System.out.println("+---------------------------------------------------------------+");
                    show(morning);
                    System.out.println("+---------------------------------------------------------------+");
                    System.out.println("|                         Afternoon                             |");
                    System.out.println("+---------------------------------------------------------------+");
                    show(afternoon);
                    System.out.println("+---------------------------------------------------------------+");
                    System.out.println("|                           Night                               |");
                    System.out.println("+---------------------------------------------------------------+");
                    show(night);
                    break;
                case "c":
                    showTime();
                    break;
                case "d":
                    showhistory(n);
//                    history();
                    break;
                case "e":
                    reboot();
                    break;
            }
        } while (!menu.equalsIgnoreCase("f"));
    }

    static String validate(String message, Scanner input, String regex) {
        while (true) {
            System.out.println(message);
            String user = input.next();
            Pattern pattern = Pattern.compile(regex);
            if (pattern.matcher(user).matches()) {
                return user;
            } else {
                System.out.println(" Wrong format");
            }
        }
    }

    private static void booking() {
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("|                         Booking                               |");
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("|                  Daily Showtime of CSTAD Hall:                |");
        System.out.println("+---------+--------------------+--------------------------------+");
        System.out.println("|    A    |       Morning      |        10:00AM - 12:30PM       |");
        System.out.println("+---------+--------------------+--------------------------------+");
        System.out.println("|    B    |      Afternoon     |        03:00PM - 05:30PM       |");
        System.out.println("+---------+--------------------+--------------------------------+");
        System.out.println("|    C    |        Night       |        07:00PM - 09:30PM       |");
        System.out.println("+---------+--------------------+--------------------------------+");
        System.out.println("Please select Showtime (A | B | C):");
        String timeSelect;
        timeSelect = validate("Please select menu: ", input, "[a-zA-Z]+");
        switch (timeSelect) {
            case "a":
                System.out.println("+---------------------------------------------------------------+");
                System.out.println("|                         Morning                               |");
                System.out.println("+---------------------------------------------------------------+");
                morning1();
                break;
            case "b":
                System.out.println("+---------------------------------------------------------------+");
                System.out.println("|                         Afternoon                             |");
                System.out.println("+---------------------------------------------------------------+");
                afternoon1();
                break;
            case "c":
                System.out.println("+---------------------------------------------------------------+");
                System.out.println("|                           Night                               |");
                System.out.println("+---------------------------------------------------------------+");
                night1();
                break;
        }
    }

    private static void hall() {
        morning();
        afternoon();
        night();
    }

    static void show(String[][] message) {
        for (int i = 0; i < message.length; i++) {
            for (int j = 0; j < message[i].length; j++) {
                if (message[i][j] == null) {
                    System.out.printf("| " + (char) ('A' + i) + "-" + (j + 1) + " - " + "AV |");
                } else {
                    System.out.printf("| " + (char) ('A' + i) + "-" + (j + 1) + " - " + "BO |");
                }
            }
            System.out.println();
        }
    }

    private static void showTime() {
        System.out.println("#Daily Showtime of CSTAD Hall:");
        System.out.println("A) Morning (10:00AM - 12:30PM)");
        System.out.println("B) Afternoon (03:00PM - 05:30PM)");
        System.out.println("C) Night (07:00PM - 09:30PM");
        menu();
    }

    static void showhistory(int n) {
        if (booking == null) {
            System.out.println("+---------------------------------------------------------------+");
            System.out.println("|                        No History                             |");
            System.out.println("+---------------------------------------------------------------+");
        }
        System.out.println("+---------------+--------------------+-----------+--------------+");
        System.out.println("|      ID       |        SHIFT       |    SEAT   |     DATE     |");
        System.out.println("+---------------+--------------------+-----------+--------------+");
        for (int i = 0; i < n; i++) {
            System.out.println(booking[i]);
        }
        System.out.println("+---------------+--------------------+-----------+--------------+");
    }

    private static void morning() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (morning[i][j] == null) {
                    System.out.printf("| " + (char) ('A' + i) + "-" + (j + 1) + " - " + "AV |");
                } else {
                    System.out.printf("| " + (char) ('A' + i) + "-" + (j + 1) + " - " + "BO |");
                }
            }
            System.out.println();
        }
    }

    public static void morning1() {
        morning();
        int bookingCount = 0;

        System.out.print("Please enter your seats: ");
        input.nextLine(); // Consume the newline character left in the buffer
        String seats = input.next();
        String id = validate("Enter student ID: ", input, "[1-9]+");
        id = String.valueOf(Integer.parseInt(id));

        // Split the seats input by commas
        String[] seatArray = seats.split(",");

        // Check if the seats are already occupied
        boolean seatAlreadyTaken = false;

        for (String seat : seatArray) {
            // Split each seat by dash
            String[] seatParts = seat.split("-");

            if (seatParts.length == 2) {
                char rowChar = seatParts[0].charAt(0);
                row1 = rowChar - 'A';
                col1 = Integer.parseInt(seatParts[1]) - 1;

                if (row >= 0 && row < rows && col >= 0 && col < cols) {
                    if (morning[row][col] != null) {
                        seatAlreadyTaken = true;
                    } else {
                        morning[row][col] = "BO";
                        bookingCount++;
                    }
                }
            }
        }
        LocalDate localDate = LocalDate.now();
        String history = "|\t\t" + id + "\t\t" + "\t\tMorning \t\t" + seats + "\t\t\t" + localDate + "\t|"; // Adjust the concatenation here
        booking[n++] = history;
        if (!seatAlreadyTaken) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (morning[i][j] == null) {
                        System.out.printf("| " + (char) ('A' + i) + "-" + (j + 1) + " - AV |");
                    } else {
                        System.out.printf("| " + (char) ('A' + i) + "-" + (j + 1) + " - BO |");
                    }
                }
                System.out.println();
            }
        } else {
            System.out.println("Some seats are already taken. Please choose other seats.");
        }

        System.out.println("Total bookings: " + bookingCount);
    }


    private static void afternoon() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (afternoon[i][j] == null) {
                    System.out.printf("| " + (char) ('A' + i) + "-" + (j + 1) + " - " + "AV |");
                } else {
                    System.out.printf("| " + (char) ('A' + i) + "-" + (j + 1) + " - " + "BO |");
                }
            }
            System.out.println();
        }
    }

    public static void afternoon1() {
        afternoon();
        int bookingCount = 0;

        System.out.print("Please enter your seats: ");
        input.nextLine(); // Consume the newline character left in the buffer
        String seats = input.nextLine();
        String id1 = validate("Enter student ID: ", input, "[1-9]+");
        id = Integer.parseInt(id1);

        // Split the seats input by commas
        String[] seatArray = seats.split(",");

        // Check if the seats are already occupied
        boolean seatAlreadyTaken = false;

        for (String seat : seatArray) {
            // Split each seat by dash
            String[] seatParts = seat.split("-");

            if (seatParts.length == 2) {
                char rowChar = seatParts[0].charAt(0);
                row1 = rowChar - 'A';
                col1 = Integer.parseInt(seatParts[1]) - 1;

                if (row >= 0 && row < rows && col >= 0 && col < cols) {
                    if (afternoon[row][col] != null) {
                        seatAlreadyTaken = true;
                    } else {
                        afternoon[row][col] = "BO";
                        bookingCount++;
                    }
                }
            }
        }
        LocalDate localDate = LocalDate.now();
        String history = "|\t\t" + id + "\t\t" + "\t\tAfternoon \t" + seats + "\t\t\t" + localDate + "\t|"; // Adjust the concatenation here
        booking[n++] = history;
        if (!seatAlreadyTaken) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (afternoon[i][j] == null) {
                        System.out.printf("| " + (char) ('A' + i) + "-" + (j + 1) + " - AV |");
                    } else {
                        System.out.printf("| " + (char) ('A' + i) + "-" + (j + 1) + " - BO |");
                    }
                }
                System.out.println();
            }
        } else {
            System.out.println("Some seats are already taken. Please choose other seats.");
        }

        System.out.println("Total bookings: " + bookingCount);
    }

    private static void night() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (night[i][j] == null) {
                    System.out.printf("| " + (char) ('A' + i) + "-" + (j + 1) + " - " + "AV |");
                } else {
                    System.out.printf("| " + (char) ('A' + i) + "-" + (j + 1) + " - " + "BO |");
                }
            }
            System.out.println();
        }
    }

    public static void night1() {
        afternoon();
        int bookingCount = 0;

        System.out.print("Please enter your seats: ");
        input.nextLine(); // Consume the newline character left in the buffer
        String seats = input.nextLine();
        String id1 = validate("Enter student ID: ", input, "[1-9]+");
        id = Integer.parseInt(id1);

        // Split the seats input by commas
        String[] seatArray = seats.split(",");

        // Check if the seats are already occupied
        boolean seatAlreadyTaken = false;

        for (String seat : seatArray) {
            // Split each seat by dash
            String[] seatParts = seat.split("-");

            if (seatParts.length == 2) {
                char rowChar = seatParts[0].charAt(0);
                row1 = rowChar - 'A';
                col1 = Integer.parseInt(seatParts[1]) - 1;

                if (row >= 0 && row < rows && col >= 0 && col < cols) {
                    if (night[row][col] != null) {
                        seatAlreadyTaken = true;
                    } else {
                        night[row][col] = "BO";
                        bookingCount++;
                    }
                }
            }
        }
        LocalDate localDate = LocalDate.now();
        String history = "|\t\t" + id + "\t\t" + "\t\tNight\t\t" + seats + "\t\t\t" + localDate + "\t|"; // Adjust the concatenation here
        booking[n++] = history;
        if (!seatAlreadyTaken) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (night[i][j] == null) {
                        System.out.printf("| " + (char) ('A' + i) + "-" + (j + 1) + " - AV |");
                    } else {
                        System.out.printf("| " + (char) ('A' + i) + "-" + (j + 1) + " - BO |");
                    }
                }
                System.out.println();
            }
        } else {
            System.out.println("Some seats are already taken. Please choose other seats.");
        }

        System.out.println("Total bookings: " + bookingCount);


    }

    private static void history() {
        System.out.println("History");
        System.out.println("Seat\t\tID");
        System.out.println(seats + "\t\t" + id);
        menu();
    }

    private static void reboot() {
        System.out.println("Do you want to reboot hall? y/n");
        String confirm = input.next();
        if ("y".equalsIgnoreCase(confirm)) {
            for (int i = 0; i < morning.length; i++) {
                for (int j = 0; j < morning[i].length; j++) {
                    morning[i][j] = null;
                }
            }
            for (int i = 0; i < afternoon.length; i++) {
                for (int j = 0; j < afternoon[i].length; j++) {
                    afternoon[i][j] = null;
                }
            }
            for (int i = 0; i < night.length; i++) {
                for (int j = 0; j < night[i].length; j++) {
                    night[i][j] = null;
                }
            }
            booking = null;
            System.out.println("Hall has been reboot");
        } else {
            System.out.println("Cancel");
        }
    }

    public static void main(String[] args) {
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("|                 WELCOME TO CSTAD HALL BOOKING                 |");
        System.out.println("+---------------------------------------------------------------+");
        String row1 = validate("Please Row of Seat: ", input, "[1-9]+");
        rows = Integer.parseInt(row1);
        String col1 = validate("Please Column of Seat: ", input, "[1-9]+");
        cols = Integer.parseInt(col1);
        morning = new String[rows][cols];
        afternoon = new String[rows][cols];
        night = new String[rows][cols];
        menu();

    }
}
