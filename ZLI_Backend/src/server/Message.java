package server;

import java.util.HashMap;

public class Message {
    private String command = null; // ! will change to be an enum
    private HashMap<String, Object> data = null;

    public Message(String command) {
        this.command = command;
        data = new HashMap<String, Object>();
    }

    public void addToData(String key, Object value) {
        data.put(key, value);
    }

    public String getCommand() {
        return this.command;
    }

    public HashMap<String, Object> getDataMap() {
        return this.data;
    }

    public Object getDataValue(String key) {
        // ? may change to return the true value of the data
        return data.get(key);
    }

}
