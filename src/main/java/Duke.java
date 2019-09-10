import java.io.IOException;
import java.util.ArrayList;
public class Duke {
    public static TaskCommands taskCommands;


    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcome();
        Boolean isExit = false;
        ArrayList<Task> store = new ArrayList<Task>();
        Storage storage = new Storage(store);
        String command;
        while (!isExit) {
            command = ui.readCommand();
            try {
                taskCommands = new TaskCommands(store, command);
                isExit = taskCommands.isExit(command);
            } catch (DukeException e) {
                ui.showError(e.error());
            }
        }
        try {
            storage.usingBufferedWritter(store);
        } catch (IOException io){
            System.out.println("OOPS!!! The file could not be created.");
        }
    }
}

