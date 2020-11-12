package view;

import controller.AdminService;

import java.util.Scanner;

public class LoginView {
    public boolean login() {
        System.out.println("Enter Your Username:");
        String username= getUserPassFromAdmin();
        System.out.println("Enter Your Password:");
        String password= getUserPassFromAdmin();
        AdminService adminService=new AdminService();
        boolean answer=adminService.verifyAdmin(username,password);
        if(answer==true){
            return true;
        }
        else{
            return false;
        }

    }



    public String getUserPassFromAdmin() {
        String input;
        Scanner scan=new Scanner(System.in);
        while(true){
            input=scan.next();
            if(input.length()>=5){
                return input;
            }
            else{
                System.out.println("Your Input is Invalid.The length is at least 5!");
            }
        }

    }
}
