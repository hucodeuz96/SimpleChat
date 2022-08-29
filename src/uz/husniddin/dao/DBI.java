package uz.husniddin.dao;

import uz.husniddin.enums.GroupStatus;
import uz.husniddin.enums.Order;
import uz.husniddin.enums.Status;
import uz.husniddin.model.*;
import uz.husniddin.model.Group;

import java.util.*;

public class DBI {
    public static Scanner scanner = new Scanner(System.in);
    public static List<User> users = new ArrayList<>(0);
    public static List<User> users1 = new ArrayList<>(0);
    public static List<User> users2 = new ArrayList<>(0);
    public static List<User> users3 = new ArrayList<>(0);
    public static List<Message> messages = new ArrayList<>(0);
    public static List<Request> requests = new ArrayList<>(0);
    public static List <Group> groups = new ArrayList<>(0);
    public static Boolean loggedin = false;
    public static User session = null;

    public static void init() {
        users.add(new User("admin", "123", Order.ADMIN, Status.ACTIVE));
        users.add(new User("user1", "user1", Order.USER, Status.ACTIVE));
        users.add(new User("user2", "user2", Order.USER, Status.ACTIVE));
        users.add(new User("user3", "user3", Order.USER, Status.ACTIVE));
        users.add(new User("user4", "user4", Order.USER, Status.ACTIVE));
        users.add(new User("user5", "user5", Order.USER, Status.ACTIVE));
        users.add(new User("user6", "user6", Order.USER, Status.ACTIVE));
        users.add(new User("user7", "user7", Order.USER, Status.ACTIVE));
        users.add(new User("user8", "user8", Order.USER, Status.ACTIVE));
        users.add(new User("user9", "user9", Order.USER, Status.ACTIVE));
        users.add(new User("user10", "user10", Order.USER, Status.ACTIVE));

        users1.add(users.get(1));
        users1.add(users.get(2));
        users1.add(users.get(3));

        users.set(1,new User("user1", "user1", Order.USER, Status.ACTIVE,GroupStatus.MEMBER));
        users2.add(users.get(1));
        users.set(5,new User("user5", "user5", Order.USER, Status.ACTIVE,GroupStatus.OWNER));
        users2.add(users.get(5));
        users2.add(users.get(6));

        users.set(4,new User("user4", "user4", Order.USER, Status.ACTIVE,GroupStatus.OWNER));
        users3.add(users.get(1));
        users3.add(users.get(8));
        users3.add(users.get(7));
        users3.add(users.get(4));

        groups.add(new Group("G7",users.get(1).getUsername(),users1));
        groups.add(new Group("G5",users.get(5).getUsername(),users2));
        groups.add(new Group("G3",users.get(4).getUsername(),users3));
    }
    public static User findByUsername(java.lang.String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
            return null;
    }
}
