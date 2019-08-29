public class Events extends Task{
    String icon;
    String time;
    public Events(String name, String time) {
        super(name);
        this.icon = "[E]";
        this.time = time;
    }
    @Override
    public String toString() {
        return icon+super.toString()+" (at: "+time+")";
    }
}
