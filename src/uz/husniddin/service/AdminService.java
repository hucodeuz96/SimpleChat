package uz.husniddin.service;

import uz.husniddin.enums.Order;
import uz.husniddin.enums.Status;

import static uz.husniddin.Main.run;
import static uz.husniddin.dao.DBI.*;

public class AdminService {
    public static void showUser(){
        for (int i = 0; i < users.size(); i++) {
            System.out.println(i+1+"-"+users.get(i));
        }
        run();
    }
    public static void blockUser(){
        for (int i = 0; i < users.size(); i++) {
            System.out.println(i+1+"-"+users.get(i));
        }
        System.out.print("Select id : ");
        int n = scanner.nextInt();
        for (int i = 0; i < users.size(); i++) {
            if (i==n-1)
                users.get(i).setStatus(Status.BLOCKED);
        }
        System.out.println("This user is Blocked");
        run();
    }
    public static void unblockUser(){
        for (int i = 0; i < users.size(); i++) {
            System.out.println(i+1+"-"+users.get(i));
        }
        System.out.print("Select id : ");
        int n = scanner.nextInt();
        for (int i = 0; i < users.size(); i++) {
            if (i==n-1)
                users.get(i).setStatus(Status.ACTIVE);
        }
        System.out.println("This user is UnBlocked");
        run();
    }
    public static void deleteUser(){
        for (int i = 0; i < users.size(); i++) {
            System.out.println(i+1+"-"+users.get(i));
        }
        System.out.print("Select id : ");
        int n = scanner.nextInt();
        for (int i = 0; i < users.size(); i++) {
            if (i==n-1)
                users.get(i).setStatus(Status.DELETED);
        }
        System.out.println("This user is Deleted");
        run();
    }
    public static void changePasswordAdmin(){
        System.out.print("Enter new password: ");
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getRole().equals(Order.ADMIN)) {
                users.get(i).setPassword(scanner.nextLine());
            }
        }
        System.out.println("Password is changed!!!");
        run();
    }
    public static void logOutAdmin(){
        loggedin=false;
        session=null;
        run();
    }
}
