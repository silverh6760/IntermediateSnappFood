package view;

import controller.AdminService;
import controller.CustomerService;
import controller.FoodService;
import controller.RestaurantService;
import model.dto.CustomerDto;
import model.dto.RestaurantDto;
import model.entity.Admin;
import model.dto.FoodDto;
import model.entity.FoodType;
import model.entity.Restaurant;

import java.util.List;
import java.util.Scanner;

public class AdminView {
    Scanner scan = new Scanner(System.in);
    AdminService adminService = new AdminService();
    RestaurantService restaurantService = new RestaurantService();
    LoginView loginView = new LoginView();
    FoodService foodService = new FoodService();
    CustomerService customerService =new CustomerService();

    public void adminLogin() {
        outer1:
        while (true) {
            System.out.println("1-Login");
            System.out.println("2-New Admin");
            System.out.println("3-Return");
            int input = GetInput.getIntFromUser(1, 3);
            switch (input) {
                case 1:
                    boolean answer = loginView.login();
                    if (answer == false) {
                        System.out.println("Your Username Or Password is wrong!");
                        continue outer1;
                    } else {
                        System.out.println("You are Logged in Successfully!");
                        adminMenu();
                        break outer1;
                    }
                case 2:
                    addNewAdmin();
                    continue outer1;
                case 3:
                    break outer1;

            }
        }
    }

    private void addNewAdmin() {
        System.out.println("Enter Your First Name");
        String firstName = GetInput.getStringFromUser();
        System.out.println("Enter Your Last Name");
        String lastName = GetInput.getStringFromUser();
        System.out.println("Enter Your Postal Code");
        String postalCode = GetInput.getStringFromUser();
        System.out.println("Enter Your Username");
        String userName = loginView.getUserPassFromAdmin();
        System.out.println("Enter Your Password");
        String password = loginView.getUserPassFromAdmin();
        Admin admin = new Admin(firstName, lastName, postalCode, userName, password);
        System.out.println(admin);
        adminService.addNewAdmin(admin);
        System.out.println(admin + " is added successfully!");
    }

    private void adminMenu() {
        adminMenu:
        while (true) {
            System.out.println("1-Add a Restaurant");
            System.out.println("2-Add a Food to a Restaurant");
            System.out.println("3-Observe Users' Report");
            System.out.println("4-Observe Restaurants' Report");
            System.out.println("5-Return");
            int input = GetInput.getIntFromUser(1, 5);
            switch (input) {
                case 1:
                    System.out.println("How many restaurant do you want to add?");
                    int countOfRest = GetInput.getInt();
                    for (int i = 0; i < countOfRest; i++)
                        addRestaurantToDB();
                    continue;

                case 2:
                    addFood();
                    break;
                case 3:
                    observeCustomerReport();
                    break;
                case 4:
                    observeRestaurantReport();
                    break;
                case 5:
                    break adminMenu;
            }
        }
    }

    private void observeRestaurantReport() {
        List<RestaurantDto> restaurantDtoList=restaurantService.observeRestaurantReport();
        restaurantDtoList.stream().forEach(System.out::println);
        final int[] array2= {1,2,3,4,5,6,7,8,9,10};
        for(int i:array2) {
            restaurantDtoList.stream().filter(r->r.getArea().equals(i)).filter(r->r.getTotalService()<=5000)
                    .forEach(r->System.out.println(r.getArea()+" =<5000 "+r.getNameOfRest()+" "+r.getNameOfFood()));
            restaurantDtoList.stream().filter(r->r.getArea().equals(i)).filter(r->r.getTotalService()>5000 && r.getTotalService()<8000)
                    .forEach(r->System.out.println(r.getArea()+" 5000<<8000 "+r.getNameOfRest()+" "+r.getNameOfFood()));
            restaurantDtoList.stream().filter(r->r.getArea().equals(i)).filter(r->r.getTotalService()>5000 && r.getTotalService()>=8000)
                    .forEach(r->System.out.println(r.getArea()+" >=8000 "+r.getNameOfRest()+" "+r.getNameOfFood()));
        }
    }

    private void observeCustomerReport() {
        List<CustomerDto> customerDtoList =customerService.observeCustomerReport();
        customerDtoList.stream().forEach(System.out::println);
        final int[] array= {1,2,3,4,5,6,7,8,9,10,11,12};
        for(int i:array) {
            customerDtoList.stream().filter(u->u.getRegisterDate().getMonth()+1==i).filter(u->u.getTotalPurchase()<100000)
                    .forEach(u->System.out.println((u.getRegisterDate().getMonth()+1)+" "+"<100 "+u.getFirstName()+" "+u.getLastName()+
                            " "+u.getPhoneNumber()));
            customerDtoList.stream().filter(u->u.getRegisterDate().getMonth()+1==i).filter(u->u.getTotalPurchase()>100000.0 && u.getTotalPurchase()<500000.0)
                    .forEach(u->System.out.println((u.getRegisterDate().getMonth()+1)+" "+"100< <500 "+u.getFirstName()+
                            " "+u.getLastName()+" "+u.getPhoneNumber()));
            customerDtoList.stream().filter(u->u.getRegisterDate().getMonth()+1==i).filter(u->u.getTotalPurchase()>500000)
                    .forEach(u->System.out.println((u.getRegisterDate().getMonth()+1)+" "+">500 "+u.getFirstName()+" "+u.getLastName()+
                            " "+u.getPhoneNumber()));
        }
    }

    private void addFood() {
        System.out.println("Enter the name of Restaurant");
        String nameOfRest = GetInput.getStringFromUser();
        System.out.println("Enter the number of Food");
        int countOfFood = GetInput.getInt();
        for (int i = 1; i <= countOfFood; i++) {
            System.out.println("Enter name of food");
            String nameOfFood = GetInput.getStringFromUser();
            FoodType foodType = getFoodTypeFromUser();
            System.out.println("Enter the price of food");
            long price = GetInput.getLongFromUser();
            System.out.println("Enter count of food");
            int count = GetInput.getInt();
            FoodDto foodDto = new FoodDto(nameOfFood, foodType, price, count);
            boolean answer = foodService.addFoodToRestaurant(foodDto, nameOfRest);
            if (answer == true)
                System.out.println(foodDto + " is added successfully into the DB");
            else
                System.out.println("The name of Food is repetitive");
        }
    }

    public FoodType getFoodTypeFromUser() {
        while (true) {
            System.out.println("Enter Type of Food");
            String type = GetInput.getStringFromUser();
            if (type.equalsIgnoreCase("iranian"))
                return FoodType.IRANIAN;
            else if (type.equalsIgnoreCase("seafood"))
                return FoodType.SEA_FOOD;
            else if (type.equalsIgnoreCase("fastfood"))
                return FoodType.FAST_FOOD;
            else {
                System.out.println("Wrong Entry for Type!Try Again...");
                continue;
            }
        }
    }


//    public int getInt() {
//        while (true) {
//            System.out.println("Enter a Integer:");
//            int input = scan.nextInt();
//            if (input >= 1)
//                return input;
//            else {
//                System.out.println("Your Input is invalid!Try again!");
//            }
//
//        }
//    }

    private void addRestaurantToDB() {
        System.out.println("Enter name Of Restaurant:");
        String nameOfRest = GetInput.getStringFromUser();
        System.out.println("Enter the restaurant Area");
        int area = GetInput.getInt();
        System.out.println("Enter the price of service");
        long service = GetInput.getLongFromUser();
        Restaurant restaurant = new Restaurant(nameOfRest, area, service);
        boolean answer = restaurantService.addNewRestaurant(restaurant);
        if (answer == true)
            System.out.println(restaurant + " is added successfully into the DB");
        else
            System.out.println("The name is repetitive");
    }

//    public long getLongFromUser() {
//        while (true) {
//            System.out.println("Enter the price:");
//            long input = scan.nextLong();
//            if (input >= 1)
//                return input;
//            else {
//                System.out.println("Your Input is invalid!Try again!");
//            }
//
//        }
//    }

//    public String getStringFromUser() {
//        while (true) {
//            String input = scan.nextLine();
//            if (input != null && input.length() > 1) {
//                return input;
//            } else {
//                System.out.println("Invalid input!Try again!");
//            }
//        }
//    }

//    public int getIntFromUser(int start, int finish) {
//
//        int input;
//        while (true) {
//            System.out.println("Enter an Option:");
//            input = scan.nextInt();
//            if (input >= start && input <= finish)
//                break;
//            else
//                System.out.println("Wrong Entry");
//        }
//        return input;
//    }
}
