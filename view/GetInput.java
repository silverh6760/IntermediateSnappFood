package view;

import java.util.Scanner;

public class GetInput {

    static Scanner scan=new Scanner(System.in);

    static public int getIntFromUser(int start, int finish) {
        int input;
        while (true) {
            System.out.println("Enter an Option:");
            input = scan.nextInt();
            if (input >= start && input <= finish)
                break;
            else
                System.out.println("Wrong Entry");
        }
        return input;
    }

   static public String getStringFromUser() {
        while (true) {
            String input = scan.nextLine();
            if (input != null && input.length() > 1) {
                return input;
            }else if(input==null) {
                System.out.println("Invalid input!Try again!");
            }
        }
    }


    static public long getLongFromUser() {
        while (true) {
            System.out.println("Enter the price:");
            long input = scan.nextLong();
            if (input >= 1)
                return input;
            else {
                System.out.println("Your Input is invalid!Try again!");
            }

        }
    }

    static public int getInt() {
        while (true) {
            System.out.println("Enter a Integer:");
            int input = scan.nextInt();
            if (input >= 1)
                return input;
            else {
                System.out.println("Your Input is invalid!Try again!");
            }

        }
    }
}
