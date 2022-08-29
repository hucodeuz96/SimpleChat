package uz.husniddin.service;

import uz.husniddin.enums.GroupStatus;
import uz.husniddin.enums.Order;
import uz.husniddin.enums.Status;
import uz.husniddin.model.Group;
import uz.husniddin.model.Message;
import uz.husniddin.model.Request;
import uz.husniddin.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static uz.husniddin.Main.run;
import static uz.husniddin.dao.DBI.*;

public class UserService {
    public static  void login(){
        scanner =new Scanner(System.in);
        System.out.print("username : ");
        String username = scanner.nextLine();
        System.out.print("password : ");
        String password = scanner.nextLine();
        User user = findByUsername(username);
        if(Objects.nonNull(user)){
            if (user.getPassword().equals(password)&&user.getStatus().equals(Status.ACTIVE)){
                session=user;
                loggedin=true;
            }
            else if(user.getPassword().equals(password)&&user.getStatus().equals(Status.BLOCKED)){
                System.out.println("This user was blocked!!!");
            }
            else{
                System.out.println(" WRONG !!!");
            }
                run();
        }
    }
    public static  void registration(){
        User user =new User();
        scanner =new Scanner(System.in);
        System.out.print("username : ");user.setUsername(scanner.nextLine());
        System.out.print("password : ");user.setPassword(scanner.nextLine());
        users.forEach(user1 -> {
            if(!user.getUsername().equals(user1.getUsername()) && !user.getPassword().equals(user1.getPassword())){
                if (user.getStatus().equals(Status.BLOCKED)){
                    System.out.println("This user was Blocked!!!");
                    run();
                }
                else {
                    System.out.println("Registration Successfully");
                    users.add(user);
                    run();
                }
            }
     });
    }
    public static void showMessage(){
        boolean g=true;
        Message message1 = new Message();
            for (Group group : groups) {
                for (int i = 0; i < group.getUserID().size(); i++) {
                    if (group.getUserID().get(i).getUsername().equals(session.getUsername())) {
                        g = !g;
                        for (Message message : messages) {
                            if (message.getToID().equals(group.getId())) {
                                System.out.println("Group :: " + group.getName());
                                System.out.println("Text :: " + message.getText());
                                System.out.println("From :: " + message.getFromID());
                                message1.setToID(group.getId());
                                message1.setId(message.getId());
                            }
                        }
                        break;
                    }
                }
            }
            for (Message message : messages) {
                if (message.getToID().equals(session.getId())) {
                    System.out.println("Text :: " + message.getText());
                    System.out.println("From :: " + message.getFromID());
                    g=false;
                    for (User user : users) {
                        if (user.getUsername().equals(message.getFromID())) {
                            message1.setToID(user.getId());
                            break;
                        }
                    }
                }
            }
        if (g==false){
            System.out.print("1-> Reply\n0-> Back\nSelect : ");
            if(scanner.nextInt()==1){
                for (Group group : groups) {
                    if (group.getId().equals(message1.getToID())) {
                        System.out.println(group);
                    }
                }
                scanner = new Scanner(System.in);
                System.out.print("text: ");
                message1.setText(scanner.nextLine());
                message1.setFromID(session.getUsername());
                messages.add(message1);
                System.out.println(messages);
                run();
            }
            else {
                run();
            }
        }
        run();
    }
    public static  void sendMessage(){
        System.out.print("1-> private group\n2-> joined group\n3-> to other user\n0-> back\nSelect : ");
        String n= scanner.nextLine();
        switch (n){
            case "1"->{
               messageForOwnerGroup();
            }
            case "2"->{
               messageForJoinedGroup();
            }
            case "3"->{
               messageForUser();
            }
            case "0"->{
                run();
            }
            default -> {
                sendMessage();
            }
        }
        run();
    }
    public static  void messageForOwnerGroup(){
        boolean bool =true;
        for (int i = 0; i < groups.size(); i++) {
            if (session.getUsername().equals(groups.get(i).getOwnerName())) {
                System.out.println(i+"-"+groups.get(i)); bool=false;
                for (User user : groups.get(i).getUserID()) {
                    System.out.println(user.getUsername());
                }
            }
        }
        if (bool==false) {
            System.out.print("\n\nSelect id(kursatilgan raqam buyicha) : ");
            scanner = new Scanner(System.in);
            int k = scanner.nextInt();
            Message message = new Message();
            message.setFromID(session.getUsername());
            System.out.println(groups.get(k).getName() + " group");
            message.setToID(groups.get(k).getId());
            scanner = new Scanner(System.in);
            System.out.print("\n\nMessage: ");
            message.setText(scanner.nextLine());
            messages.add(message);
            System.out.println(messages);
        }
        else {
            System.out.println("You haven't a privite group");
        }
        sendMessage();
    }
    public static  void messageForJoinedGroup() {
        boolean bool = true;
        for (Group group : groups) {
            for (User user : group.getUserID()) {
                if (session.equals(user)) {
                    System.out.println("name : "+group.getName()+" owner-> "+group.getOwnerName());
                    for (User user1 : group.getUserID()) {
                        System.out.println(user1);
                    }
                    bool=false;
                    break;
                }
            }
        }
        if (bool == false) {
            System.out.print("\n\nEnter group name(G5,G3,G7 shunga uxshagan guruh nomini kiriting) : ");
            String k = scanner.nextLine();
            Message message = new Message();
            message.setFromID(session.getUsername());
            for (Group group : groups) {
                if (group.getName().equals(k)) {
                    message.setToID(group.getId());
                }
            }
            scanner = new Scanner(System.in);
            System.out.print("\n\nMessage: ");
            message.setText(scanner.nextLine());
            messages.add(message);
            System.out.println(messages);
        }
        else {
            System.out.println("You haven't a joined group");
        }
        sendMessage();
    }
    public static void messageForUser(){
        Message message = new Message();
        for (int i = 0; i < users.size(); i++) {
            if (!session.equals(users.get(i))) {
                 System.out.println(i+"-"+users.get(i));
            }
        }
        message.setFromID(session.getUsername());
        System.out.print("Select id : ");
        int s= scanner.nextInt();
        message.setToID(users.get(s).getId());
        System.out.print("Text : ");
        scanner = new Scanner(System.in);
        message.setText(scanner.nextLine());
        messages.add(message);
        System.out.println(messages);
        System.out.println(session);
        sendMessage();
    }
    public static void showGroup(){
        for (Group group : groups) {
            for (int i = 0; i < group.getUserID().size(); i++) {
                    System.out.println(group+"  owner : "+group.getOwnerName());
                    for (int j = 0; j < group.getUserID().size(); j++) {
                        System.out.println(group.getUserID().get(j).getUsername());
                    }
                    break;
            }
        }
        run();
    }
    public static  void creatGroup(){
        Group group = new Group();
        List<User> userList = new ArrayList<>(0);
        System.out.print("new groupName :  ");
        scanner = new Scanner(System.in);
        group.setName(scanner.nextLine());
        group.setOwnerName(session.getUsername());
        session.setGroupStatus(GroupStatus.OWNER);
        userList.add(session);
        boolean bool =true;
        while (bool) {
            for (User user : users) {
                if (user.getGroupStatus().equals(GroupStatus.NOT))
                    System.out.println(user);
            }
            System.out.print("\n\nwho is joining (user1,user2... shunga uxshagan username larni birini kiriting!):");
            scanner = new Scanner(System.in);
            String select = scanner.nextLine();
            for (User user : users) {
                if (user.getUsername().equals(select)) {
                    user.setGroupStatus(GroupStatus.MEMBER);
                    userList.add(user);
                }
            }
            System.out.print("1-> Join again(yana user qushasizmi)\n0-> Enough(shunchasi yetadi bizga)\nSelect :  ");
            int l = scanner.nextInt();
            if (l == 1)
                bool = true;
            else
                bool = false;
        }
        group.setUserID(userList);
        System.out.println(group);
        System.out.println(userList);
        groups.add(group);
        run();
    }
    public static void showRequest() {
        boolean r=false;boolean t=false;
        for (Request request : requests) {
            if (request.getGroupOwner().equals(session.getUsername())) {
                r=true;
            }
            if (request.getRequsetFrom().equals(session)) {
                t=true;
            }
        }
        if (r==true)
        {
            User user = new User();
            for (Group group : groups) {
                for (int i = 0; i < group.getUserID().size(); i++) {
                    System.out.println(group+"  owner : "+group.getOwnerName());
                    for (int j = 0; j < group.getUserID().size(); j++) {
                        System.out.println(group.getUserID().get(j).getUsername());
                    }
                    break;
                }
            }
            for (Request request : requests) {
                for (Group group : groups) {
                    if (group.getName().equals(request.getGroupTo())) {
                        System.out.println("Group :: "+group);
                        System.out.println(request.getDescription());
                        System.out.println(request.getRequsetFrom());
                    }
                }
            }
            System.out.print("1-> permission(ruxsat berasizmi)\n2-> cancel(bekor qilasizmi)\nSelect : ");
            int n= scanner.nextInt();
            switch (n){
                case 1->{
                    for (Request request : requests) {
                        for (Group group : groups) {
                            if (group.getName().equals(request.getGroupTo())) {
                                request.getRequsetFrom().setGroupStatus(GroupStatus.MEMBER);
                                request.setStatus(GroupStatus.MEMBER);
                                group.getUserID().add(request.getRequsetFrom());
                                System.out.println("Joined!!!");
                            }
                        }
                    }
                    run();
                }
                case 2->{
                    System.out.println("Cancelled!!!");
                    run();
                }
                default -> {
                    run();
                }
            }
        }
        else if (t==true){
            System.out.println(requests);
            run();
        }
        else {
            System.out.println("You haven't any request");
            run();
        }
    }
    public static  void requestGroup(){
        System.out.println("guruhga qushilish uchun surov junatilsa, guruh ning" +"\n"+
                " kursatilgan owner i guruhga qushaoladi yani logout qilib owner login qilishiz k.k");
        Request  request = new Request();
        for (Group group : groups) {
            for (int i = 0; i < group.getUserID().size(); i++) {
                System.out.println(group+"  owner : "+group.getOwnerName());
                for (int j = 0; j < group.getUserID().size(); j++) {
                    System.out.println(group.getUserID().get(j).getUsername());
                }
                break;
            }
        }
        System.out.print("\n\nEnter group name(ruxsat suramoqchi bulgan guruhiz nomini kiriting!) : ");
        String str = scanner.nextLine();
        request.setGroupTo(str);
        for (Group group : groups) {
            if (group.getName().equals(str)) {
                request.setGroupOwner(group.getOwnerName());
            }
        }
        request.setStatus(GroupStatus.NOT);
        request.setRequsetFrom(session);
        request.setDescription("Description :: Join to group");
        System.out.println("sending!!!");
        requests.add(request);
        System.out.println(request);
        run();

    }
    public static void changePasswordUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new password : ");
        for (User user : users) {
            if (user.equals(session)) {
                user.setPassword(scanner.nextLine());
            }
        }
        System.out.println("Password is changed!!!");
        for (User user : users) {
            if (session.equals(user)) {
                 System.out.println(user);
            }
        }
    }
    public static void logOutUser(){
        loggedin=false;
        session=null;
        run();
    }


}
