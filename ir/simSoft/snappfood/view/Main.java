package ir.simSoft.snappfood.view;

import ir.simSoft.snappfood.config.SpringContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class Main {

//    static AdminView adminView = new AdminView();
//    static CustomerView customerView = new CustomerView();
    static Scanner scan=new Scanner(System.in);

//    static ApplicationContext applicationContext = new
//            ClassPathXmlApplicationContext("applicationContext.xml");

    static  ApplicationContext appCtx=new AnnotationConfigApplicationContext(SpringContext.class);

     static AdminView adminView = appCtx.getBean(AdminView.class);
     static CustomerView customerView=appCtx.getBean(CustomerView.class);
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
