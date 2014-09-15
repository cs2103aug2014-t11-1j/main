package controller;

public class Task {

    private String event;
    private String start = "CHEESEBURGERS";
    private String end = "CHEESEBURGERS";
    private String date = "CHEESEBURGERS";

    public Task(String str) {
        event = new String();

        if (str.contains("[") && str.contains("]")) {

            String input = str.substring(0, str.indexOf('['));
            setEvent(input);

            String date = str.substring(str.indexOf('[') + 1, str.indexOf(']'));
            setDate(date);

            String newstr = str.substring(str.indexOf(']') + 1);

            if (newstr.contains("[") && newstr.contains("]")) {

                String start = newstr.substring(newstr.indexOf('[') + 1, newstr.indexOf(']'));
                setStart(start);
                newstr = newstr.substring(newstr.indexOf(']'));
                if (newstr.contains("[")) {

                    String end = newstr.substring(newstr.indexOf('['));
                    setEnd(end);
                }
            }

        } else {
            setEvent(str);
        }
    }

    public void setEvent(String e) {
        event = e;
    }

    public void setStart(String s) {
        start = s;
    }

    public void setEnd(String endt) {
        end = endt;
    }

    public void setDate(String d) {
        date = d;
    }

    public String getEvent() {
        return event;
    }

    public String getDate() {
        return date;
    }

    public String toString() {
        if (start.equals("CHEESEBURGERS") && end.equals("CHEESEBURGERS") && date.equals("CHEESEBURGERS")) {
            return event;
        } else if (start.equals("CHEESEBURGERS") && end.equals("CHEESEBURGERS")) {
            return event + "by " + date;
        } else if (end.equals("CHEESEBURGERS")) {
            return event + "by " + date + " from " + start;
        } else {
            return event + "by " + date + " from " + start + " to " + end;
        }

    }
}
