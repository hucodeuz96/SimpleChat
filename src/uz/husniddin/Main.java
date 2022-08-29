package uz.husniddin;

import uz.husniddin.enums.Order;
import uz.husniddin.model.Message;
import uz.husniddin.model.User;
import uz.husniddin.service.UserService;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static uz.husniddin.dao.DBI.*;
import static uz.husniddin.service.AdminService.*;
import static uz.husniddin.service.UserService.*;

public class Main {
    public static void main(String[] args) {
        init();
        run();
    }
    public static void run (){
      menu();
      scanner =new Scanner(System.in);
      String n= scanner.nextLine();
      if (!loggedin){
          switch (n){
              case "1"->  UserService.login();
              case "2"->  UserService.registration();
              default ->  System.out.println("Wrong Number");
          }
      }
      else if (loggedin && session.getRole().equals(Order.ADMIN)){
            switch (n){
               case "1"-> showUser();
               case "2"-> blockUser();
               case "3"-> unblockUser();
               case "4"-> deleteUser();
               case "5"-> changePasswordAdmin();
               case "0"-> logOutAdmin();
               default -> System.out.println("Error!!!");
            }
      }
      else if (loggedin && session.getRole().equals(Order.USER)){
            switch (n){
                case "1"-> showMessage();
                case "2"-> sendMessage();
                case "3"-> showGroup();
                case "4"-> creatGroup();
                case "5"-> showRequest();
                case "6"-> requestGroup();
                case "7"-> changePasswordUser();
                case "0"-> logOutUser();
                default -> System.out.println("Error!!!");

            }
      }
      run();
    }

    public static void menu(){
       if (loggedin && session.getRole().equals(Order.ADMIN)){
           System.out.print("1-> show Users\n2-> bock of user\n3-> unBlock of user\n" +
                   "4-> delete of user\n5-> Change password\n0-> logOut\nSelect :");
       }
       else if (loggedin && session.getRole().equals(Order.USER)){
           System.out.print("1-> Show Messages\n2-> Send Message\n3-> Show Group\n4-> Create Group\n" +
                     "5-> Show request(permission)\n6-> Send request(join to group)\n7-> Change password\n"+"0-> LogOut \nSelect: ");
       }
       else {
           System.out.print("1-> Login\n2-> Registration\nSelect :");
       }

    }
}
