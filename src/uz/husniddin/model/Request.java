package uz.husniddin.model;

import uz.husniddin.enums.GroupStatus;

import static uz.husniddin.util.Utils.generateUniqueID;

public class Request {
    private String id;
    private User requsetFrom;
    private String groupTo;
    private String description;
    private String groupOwner;
    private GroupStatus status;

    public Request() {
        this.id = generateUniqueID();
    }

    public Request(User requsetFrom, String groupTo, String description,String groupOwner, GroupStatus status) {
        this();
        this.requsetFrom = requsetFrom;
        this.groupTo = groupTo;
        this.description = description;
        this.groupOwner = groupOwner;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getRequsetFrom() {
        return requsetFrom;
    }

    public void setRequsetFrom(User requsetFrom) {
        this.requsetFrom = requsetFrom;
    }

    public String getGroupTo() {
        return groupTo;
    }

    public void setGroupTo(String groupTo) {
        this.groupTo = groupTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupOwner() {
        return groupOwner;
    }

    public void setGroupOwner(String groupOwner) {
        this.groupOwner = groupOwner;
    }

    public GroupStatus getStatus() {
        return status;
    }

    public void setStatus(GroupStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requsetFrom=" + requsetFrom +
                ", groupTo='" + groupTo + '\'' +
                ", description='" + description + '\'' +
                ", groupOwner=" + groupOwner +
                ", status=" + status +
                '}';
    }
}
