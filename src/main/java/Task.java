public class Task {
    protected String name;
    protected boolean isDone;
    protected String taskType;

    public Task(String name) {
        this.name = name;
    } //must add this when using class inheritance

    public Task(String name, boolean isDone, String taskType) //class constructor with 2 parameters
    {
        this.taskType = taskType;
        this.name = name;
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "[#]" : "[X]"); //get tick or X symbols
    }

    public String getTask() {
        taskType = taskType.equals("todo") ? "[T]" : taskType.equals("deadline") ? "[D]" : "[E]";
        return taskType;
    }

    @Override
    public String toString() {
        return this.getStatus()+" "+name;
    }
}
