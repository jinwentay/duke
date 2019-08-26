import java.util.Scanner;
import java.util.ArrayList;
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
        ArrayList<String> store = new ArrayList<String>();
        int num = 1;
        while (!command.equals("bye")) {
            if (!command.equals("list")) {
                //add command to the list
                store.add(command);
                System.out.println("added: "+command);
            } else {
                //print list
                for (String i : store) {
                    System.out.print(num+". ");
                    System.out.println(i);
                    num++;
                }
            }
            command = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

