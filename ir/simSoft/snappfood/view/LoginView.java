package ir.simSoft.snappfood.view;

import ir.simSoft.snappfood.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class LoginView {
    AdminService adminService;
    @Autowired
    public LoginView(AdminService adminService) {
        this.adminService = adminService;
    }

    public boolean login() {
        System.out.println("Enter Your Username:");
        String username= getUserPassFromAdmin();
        System.out.println("Enter Your Password:");
        String password= getUserPassFromAdmin();

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
