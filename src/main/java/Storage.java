import java.io.*;
import java.util.ArrayList;

public class Storage {
    String fileContent = "";
    public Storage(ArrayList<Task> store) {
        try {
            readFile(fileContent, store);
        } catch (IOException e) {
            System.out.println("OOPS!!! There is no existing file to be read.");
        }
    }
    public static void readFile(String fileContent, ArrayList<Task> store) throws IOException {
        File fileToAppend = new File("duke.txt");
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(fileToAppend));
        String line = reader.readLine();
        //write current items to file
        while (line != null) {
            fileContent += line + System.lineSeparator();
            String[] task = line.split(" ", 3);
            if (task[0].contains("[T]")) {
                Task obj = new ToDos(task[1]);
                if (task[0].contains("[#]")) obj.isDone = true;
                store.add(obj);
            } else if (task[0].contains("[D]")) {
                String[] date = task[2].split(": ");
                date[1] = date[1].substring(0, date[1].length()-1);//remove )
                Task obj = new Deadlines(task[1], date[1]);
                if (task[0].contains("[#]")) obj.isDone = true;
                store.add(obj);
            } else if (task[0].contains("[E]")) {
                String[] date = task[2].split(": ");
                date[1] = date[1].substring(0, date[1].length()-1);//remove )
                Task obj = new Events(task[1], date[1]);
                if (task[0].contains("[#]")) obj.isDone = true;
                store.add(obj);
            }
            line = reader.readLine();
        }
    }
    public void usingBufferedWritter(ArrayList<Task> store) throws IOException
    {
        for (Task i : store) {
            fileContent += i.toString() + "\n";
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("duke.txt"));
        writer.write(fileContent);
        writer.close();
    }
}
