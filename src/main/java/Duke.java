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
        ArrayList<Task> store = new ArrayList<Task>();
        int num = 1; // count num of tasks added

        while (!command.equals("bye")) {
            String[] done = command.split(" ");
            if (!command.equals("list") && !done[0].equals("done")) {
                Task obj = new Task(command, false);
                //add command to the list
                store.add(obj);
                System.out.println("added: "+command);
            } else if (command.equals("list")){
                //print list
                for (Task i : store) {
                    System.out.print(num+". ");
                    System.out.println(i.getIcon()+" "+i.name);
                    num++;
                }
            } else {
                //mark task as done
                int tasknum = Integer.parseInt(done[1]); //convert string to int
                tasknum -= 1;
                store.get(tasknum).isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(store.get(tasknum).getIcon() + " " + store.get(tasknum).name);
            }
            command = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

