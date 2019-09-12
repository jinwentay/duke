import java.io.IOException;
import java.util.ArrayList;
public class Duke {
    public static TaskCommands taskCommands;



    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcome();
        Boolean isExit = false;
        Storage storage = new Storage("duke.txt");
        String command;
        while (!isExit) {
            command = ui.readCommand();
            try {
                taskCommands = new TaskCommands(Storage.load(), command);
                isExit = taskCommands.isExit(command);
            } catch (DukeException e) {
                ui.showError(e.error());
            }
        }
        try {
            storage.usingBufferedWritter();
        } catch (IOException io){
            System.out.println("OOPS!!! The file could not be created.");
        }
    }

//    public class Duke {
//
//        private Storage storage;
//        private TaskList tasks;
//        private Ui ui;
//
//        public Duke(String filePath) {
//            ui = new Ui();
//            storage = new Storage(filePath);
//            try {
//                tasks = new TaskList(storage.load());
//            } catch (DukeException e) {
//                ui.showLoadingError();
//                tasks = new TaskList();
//            }
//        }
//
//        public void run() {
//            ui.showWelcome();
//            boolean isExit = false;
//            while (!isExit) {
//                try {
//                    String fullCommand = ui.readCommand();
//                    ui.showLine();
//                    Command c = Parser.parse(fullCommand);
//                    c.execute(tasks, ui, storage);
//                    isExit = c.isExit();
//                } catch (DukeException e) {
//                    ui.showError(e.getMessage());
//                } finally {
//                    ui.showLine();
//                }
//            }
//        }
//
//        public static void main(String[] args) {
//            new Duke("data/tasks.txt").run();
//        }
//    }
}

