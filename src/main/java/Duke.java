import java.text.DateFormat;
import java.text.ParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Duke {
    public static void listTasks(int num, ArrayList<Task> store) {
        System.out.println("Here are the tasks in your list:");
        for (Task i : store) {
            System.out.print(num+". ");
            System.out.println(i.toString());
            num++;
        }
    }

    public static void usingBufferedWritter(String fileContent, ArrayList<Task> store) throws IOException
    {
        File fileToAppend = new File("duke.txt");
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(fileToAppend));
        String line = reader.readLine();
        //write current items to file
        while (line != null) {
            fileContent += line + System.lineSeparator();
            line = reader.readLine();
        }
        for (Task i : store) {
            fileContent += i.toString() + "\n";
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("duke.txt"));
        writer.write(fileContent);
        writer.close();
    }

    static void modifyFile(String oldString, String newString)
    {
        File fileToBeModified = new File("duke.txt");

        String oldContent = "";

        BufferedReader reader = null;

        FileWriter writer = null;

        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));

            //Reading all the lines of input text file into oldContent

            String line = reader.readLine();

            while (line != null)
            {
                oldContent = oldContent + line + System.lineSeparator();

                line = reader.readLine();
            }

            //Replacing oldString with newString in the oldContent

            String newContent = oldContent.replaceAll(oldString, newString);

            //Rewriting the input text file with newContent

            writer = new FileWriter(fileToBeModified);

            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //Closing the resources

                reader.close();

                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static Date StringToDate(String original) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date newdate = null;
        try {
            newdate = formatter.parse(original);
        } catch (ParseException p) {
            System.out.println("Please key in the date and time dd/MM/yyyy HHmm format.");
        }
        return newdate;
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

        ArrayList<Task> store = new ArrayList<Task>();
        int num = 1; // count num of tasks added
        int index = 0;
        String fileContent = "";
        while (!command.equals("bye")) {
            String[] done = command.split(" ", 2);
            String[] deadline = command.split("/by ");
            String[] event = command.split("/at ");
            if (!command.equals("list") && !done[0].equals("done")) { //to do, deadline, event
                //add command to the list (to-do/deadline/event/delete)
                if (done[0].equals("todo")) {
                    try {
                        //Task obj = new Task(done[1], false, done[0]);
                        Task obj = new ToDos(done[1]);
                        store.add(obj);

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (done[0].equals("deadline")) {
                    try {
                        String[] Dtask = done[1].split("/by ");
                        Date date = StringToDate(deadline[1]);
                        DateFormat dateformat =new SimpleDateFormat("dd MMMM yyyy, HHmm");
                        String sdate = dateformat.format(date);
                        Task obj = new Deadlines(Dtask[0], sdate);
                        store.add(obj);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                    }
                } else if (done[0].equals("event")){
                    try {
                        String[] Etask = done[1].split("/at ");
                        Date date = StringToDate(event[1]);
                        DateFormat dateformat =new SimpleDateFormat("dd MMMM yyyy, HHmm");
                        String sdate = dateformat.format(date);
                        Task obj = new Events(Etask[0], sdate);
                        store.add(obj);

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (done[0].equals("delete")) {
                    int toDelete = Integer.parseInt(done[1]) - 1;
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + store.get(toDelete).toString());
                    store.remove(toDelete);
                } else {
                    try {
                        throw new DukeException(done[0]);
                    }
                    catch (DukeException e) {
                        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }

                //print task statements
                try {
                    if (!done[0].equals("delete")) {
                        String empty = store.get(index).toString();
                        System.out.println("Got it. I've added this task: ");
                        System.out.println("  " + store.get(index).toString());
                        index++;
                    } else {
                        index--;
                    }
                    System.out.println("Now you have " + Integer.toString(index) + " tasks in the list");
                } catch (NullPointerException e) {
                }

            } else if (command.equals("list")){
                listTasks(num, store);
            } else {
                //mark task as done
                int tasknum = Integer.parseInt(done[1]); //convert string to int
                tasknum -= 1;
                String old = store.get(tasknum).toString();
                store.get(tasknum).isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  "+store.get(tasknum).toString());
                modifyFile(old, store.get(tasknum).toString());
            }
            command = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        try {
            usingBufferedWritter(fileContent,store);
        } catch (IOException io){
            System.out.println("OOPS!!! The file could not be created.");
        }
    }
}

