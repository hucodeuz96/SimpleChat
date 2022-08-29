package uz.husniddin.model;

import lombok.Getter;
import lombok.Setter;
import uz.husniddin.enums.GroupStatus;
import uz.husniddin.enums.Order;
import uz.husniddin.enums.Status;

import static uz.husniddin.util.Utils.generateUniqueID;


public class User {
    private String id;
    private String username;
    private String password;
    private Order role = Order.USER;
    private Status status = Status.ACTIVE;
    private GroupStatus groupStatus =GroupStatus.NOT;


    public User() {
        this.id=generateUniqueID();
    }

    public User(String username, String password, Order role, Status status) {
        this();
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public User(String username, String password, Order role, Status status,GroupStatus groupStatus) {
        this();
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
        this.groupStatus = groupStatus;
    }

    public User(String id, String username) {
        this();
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Order getRole() {
        return role;
    }

    public void setRole(Order role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public GroupStatus getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(GroupStatus groupStatus) {
        this.groupStatus = groupStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}



