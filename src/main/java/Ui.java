import java.util.Scanner;

public class Ui {

    public void showError(String e) {
        System.out.println(e);
    }

    public void showWelcome() {
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
    }
    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}


