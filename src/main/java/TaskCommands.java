import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TaskCommands {
    public TaskCommands(ArrayList<Task> store, String command) throws DukeException {
        int num = 1; // count num of tasks added
        String[] done = command.split(" ", 2);
        String[] deadline = command.split("/by ");
        String[] event = command.split("/at ");
        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else {
            if (!command.equals("list") && !done[0].equals("done")) { //to do, deadline, event
                //add command to the list (to-do/deadline/event/delete)
                if (done[0].equals("todo")) {
                    try {
                        Task obj = new ToDos(done[1]);
                        store.add(obj);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("todo");
                    }
                } else if (done[0].equals("deadline")) {
                    try {
                        String[] Dtask = done[1].split("/by ");
                        Date date = StringToDate(deadline[1]);
                        DateFormat dateformat = new SimpleDateFormat("dd MMMM yyyy, HHmm");
                        String sdate = dateformat.format(date);
                        Task obj = new Deadlines(Dtask[0], sdate);
                        store.add(obj);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("deadline");
                    }
                } else if (done[0].equals("event")) {
                    try {
                        String[] Etask = done[1].split("/at ");
                        Date date = StringToDate(event[1]);
                        DateFormat dateformat = new SimpleDateFormat("dd MMMM yyyy, HHmm");
                        String sdate = dateformat.format(date);
                        Task obj = new Events(Etask[0], sdate);
                        store.add(obj);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("event");
                    }
                } else if (done[0].equals("delete")) {
                    int toDelete = Integer.parseInt(done[1]) - 1;
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + store.get(toDelete).toString());
                    store.remove(toDelete);
                } else if (done[0].equals("find")) {
                    try {
                        search(done[1], store);
                    } catch (NumberFormatException ex) {
                        System.out.println("OOPS!! ");
                    } catch (ArrayIndexOutOfBoundsException a) {
                        throw new DukeException("find");
                    }
                } else {
                    throw new DukeException("unknown");
//                    try {
//                        throw new DukeException(done[0]);
//                    } catch (DukeException e) {
//                        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
//                    }
                }

                //print task statements
                try {
                    if (!done[0].equals("delete") && !done[0].equals("find")) {
                        System.out.println("Got it. I've added this task: ");
                        System.out.println("  " + store.get(store.size() - 1).toString());
                    }
                    System.out.println("Now you have " + store.size() + " tasks in the list");
                } catch (NullPointerException e) {
                    throw new DukeException("null");
                }

            } else if (command.equals("list")) {
                listTasks(num, store);
            } else {
                //mark task as done
                int tasknum = Integer.parseInt(done[1]); //convert string to int
                tasknum -= 1;
                String old = store.get(tasknum).toString();
                store.get(tasknum).isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + store.get(tasknum).toString());
                modifyFile(old, store.get(tasknum).toString());
            }
        }
    }

    public boolean isExit(String command) {
        if (command.equals("bye")) {
            return true;
        } else {
            return false;
        }
    }
    private static Date StringToDate(String original) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date newdate = null;
        try {
            newdate = formatter.parse(original);
        } catch (ParseException p) {
            System.out.println("Please key in the date and time dd/MM/yyyy HHmm format.");
        }
        return newdate;
    }
    private static void listTasks(int num, ArrayList<Task> store) {
        System.out.println("Here are the tasks in your list:");
        for (Task i : store) {
            System.out.print(num+". ");
            System.out.println(i.toString());
            num++;
        }
    }
    private static void search(String word, ArrayList<Task> store) {
        System.out.println("Here are the matching tasks in your list:");
        int num = 1;
        for (Task i : store){
            try {
                if (i.name.contains(word)) {
                    System.out.println(num + ". " + i.toString());
                    num++;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("OOPS!! "+word+" cannot be found in the list. Try another word.");
            }
        }
    }
    private static void modifyFile(String oldString, String newString)
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
}
