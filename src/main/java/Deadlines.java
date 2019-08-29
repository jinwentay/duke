public class Deadlines extends Task {
    String icon;
    String date;
    public Deadlines(String name, String date) {
        super(name);
        this.icon = "[D]";
        this.date = date;
    }

    @Override
    public String toString() {
        return icon+super.toString()+" (by: "+date+")";
    }
}
