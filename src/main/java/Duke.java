import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void listTasks(int num, ArrayList<Task> store) {
        System.out.println("Here are the tasks in your list:");
        for (Task i : store) {
            System.out.print(num+". ");
            System.out.println(i.getTask()+i.getStatus()+" "+i.name);
            num++;
        }
    }

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

        Task[] tasks = new Task[100];
        ArrayList<Task> store = new ArrayList<Task>();
        int num = 1; // count num of tasks added
        int index = 0;

        while (!command.equals("bye")) {
            String[] done = command.split(" ", 2);
            String[] deadline = command.split("/by ");
            String[] event = command.split("/at ");

            if (!command.equals("list") && !done[0].equals("done")) { //to do, deadline, event
                System.out.println("Got it. I've added this task: ");

                //add command to the list
                if (done[0].equals("todo")) {
                    Task obj = new Task(done[1], false, done[0]);
                    store.add(obj);
                    tasks[index] = new ToDos(done[1]);
                } else if (done[0].equals("deadline")) {
                    String[] Dtask = done[1].split("/by ");
                    Task obj = new Task(Dtask[1], false, done[0]);
                    store.add(obj);
                    tasks[index] = new Deadlines(Dtask[0], deadline[1]);
                } else {
                    String[] Etask = done[1].split("/at ");
                    Task obj = new Task(Etask[1], false, done[0]);
                    store.add(obj);
                    tasks[index] = new Events(Etask[0], event[1]);
                }

                System.out.println("  "+tasks[index].toString());
                System.out.println("Now you have "+Integer.toString(index+1)+" tasks in the list");
                index++;
            } else if (command.equals("list")){
                listTasks(num, store);
            } else {
                //mark task as done
                int tasknum = Integer.parseInt(done[1]); //convert string to int
                tasknum -= 1;
                store.get(tasknum).isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  "+tasks[tasknum].toString());
            }
            command = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

