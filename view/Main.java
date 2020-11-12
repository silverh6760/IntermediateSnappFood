package view;

import java.util.Scanner;

public class Main {

    static AdminView adminView = new AdminView();
    static CustomerView customerView = new CustomerView();
    static Scanner scan=new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            System.out.println("1-Admin");
            System.out.println("2-Customer");
            int input = GetInput.getIntFromUser(1, 2);
            switch (input) {
                case 1:
                    adminView.adminLogin();
                    continue;
                case 2:
                    customerView.customerPanel();
            }
        }
    }
}
