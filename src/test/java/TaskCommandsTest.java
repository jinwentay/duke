import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TaskCommandsTest {
    ArrayList<Task> store = new ArrayList<>();
    @Test
    public void Test() {
        try {
            TaskCommands a = new TaskCommands(store, "todo testing");
            TaskCommands b = new TaskCommands(store, "deadline testing /by 12/12/12 0257");
            TaskCommands c = new TaskCommands(store, "event testing /at 01/11/19 0000");
            TaskCommands d = new TaskCommands(store, "find test");
            TaskCommands e = new TaskCommands(store, "delete 1");
            TaskCommands f = new TaskCommands(store, "list");
            TaskCommands g = new TaskCommands(store, "done 1");
            TaskCommands h = new TaskCommands(store, "bye");
        } catch (DukeException ex) {
            assert false;
        }
    }
}
