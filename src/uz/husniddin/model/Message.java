package uz.husniddin.model;


import static uz.husniddin.util.Utils.generateUniqueID;

public class Message {
    private String id;
    private String text;
    private String toID;
    private String fromID;


    public Message() {
        this.id = generateUniqueID();
    }

    public Message(String id, String text, String toID, String fromID) {
        this();
        this.text = text;
        this.toID = toID;
        this.fromID = fromID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getToID() {
        return toID;
    }

    public void setToID(String toID) {
        this.toID = toID;
    }

    public String getFromID() {
        return fromID;
    }

    public void setFromID(String fromID) {
        this.fromID = fromID;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", toID='" + toID + '\'' +
                ", fromID='" + fromID + '\'' +
                '}';
    }
}
