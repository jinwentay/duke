public class ToDos extends Task {
    public static String icon;
    public ToDos(String name) {
        super(name);
        this.icon = "[T]";
    }

    @Override
    public String toString() {
        return icon+super.toString();
    }
}
