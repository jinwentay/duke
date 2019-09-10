public class DukeException extends Exception{
    String errorType;
    public DukeException(String s) {
        super(s);
        this.errorType = s;
    }
    public String error() {
        if (errorType.equals("null")) {
            return "OOPS!! The description of a task cannot be empty.";
        } else if (errorType.equals("unknown")) {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        } else if (errorType.equals("todo")) {
            return "OOPS!!! The description of a todo cannot be empty.";
        } else if (errorType.equals("deadline")) {
            return "OOPS!!! The description of a deadline cannot be empty.";
        } else if (errorType.equals("event")) {
            return "OOPS!!! The description of an event cannot be empty.";
        } else if (errorType.equals("find")) {
            return "Please key in the word you are searching for!";
        } else {
            return "Sorry some error occurred. Please try again.";
        }
    }

//    public void InvalidTask(String s) {
//        try {
//            throw new DukeException(s);
//        }
//        catch (DukeException e) {
//            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
//        }
//    }

}
