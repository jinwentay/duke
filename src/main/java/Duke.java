import java.io.IOException;
import java.util.ArrayList;
public class Duke {
    private static TaskCommands taskCommands;

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
        Boolean isExit = false;
        ArrayList<Task> store = new ArrayList<Task>();
        Storage storage = new Storage(store);
        while (!isExit) {
            taskCommands = new TaskCommands(store);
            isExit = taskCommands.isExit();
        }
        try {
            storage.usingBufferedWritter(store);
        } catch (IOException io){
            System.out.println("OOPS!!! The file could not be created.");
        }
    }
}

