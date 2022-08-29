package uz.husniddin.model;

import uz.husniddin.enums.GroupStatus;
import uz.husniddin.enums.Order;
import uz.husniddin.enums.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static uz.husniddin.util.Utils.generateUniqueID;


public class Group {
    private String id;
    private String name;
    private String ownerName;
    private List<User> userID;
    private Message messages;


    public Group() {
        this.id=generateUniqueID();
    }

    public Group(String name, String ownerName) {
        this();
        this.name = name;
        this.ownerName = ownerName;
    }

    public Group(String name, String ownerName, List<User> userID) {
        this();
        this.name = name;
        this.ownerName = ownerName;
        this.userID = userID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public List<User> getUserID() {
        return userID;
    }

    public void setUserID(List<User> userID) {
        this.userID = userID;
    }

    public Message getMessages() {
        return messages;
    }

    public void setMessages(Message messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                '}';
    }
}
