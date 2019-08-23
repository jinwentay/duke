import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________\n"
                + logo);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        while (!command.equals("bye")) {
            System.out.println(command);
            command = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

