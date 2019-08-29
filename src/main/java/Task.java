public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name, boolean isDone)
    {
        this.name = name;
        this.isDone = false;
    }

    public String getIcon() {
        return (isDone ? "\u2713" : "\u2718"); //get tick or X symbols
    }
}
