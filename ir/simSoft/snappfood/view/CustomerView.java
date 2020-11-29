package ir.simSoft.snappfood.view;

import ir.simSoft.snappfood.service.CustomerService;
import ir.simSoft.snappfood.service.FoodService;
import ir.simSoft.snappfood.service.ReservationService;
import ir.simSoft.snappfood.service.RestaurantService;
import ir.simSoft.snappfood.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
@Component
public class CustomerView {
    Scanner scan = new Scanner(System.in);
    String phoneNumber;

    RestaurantService restaurantService;
    FoodService foodService;
    CustomerService customerService;
    ReservationService reservationService;
    AdminView adminView;
    @Autowired
    public CustomerView(RestaurantService restaurantService, FoodService foodService, CustomerService customerService,
                        ReservationService reservationService, AdminView adminView) {
        this.restaurantService = restaurantService;
        this.foodService = foodService;
        this.customerService = customerService;
        this.reservationService = reservationService;
        this.adminView = adminView;
    }

    public void customerPanel() {
        phoneNumber = getPhoneNumber();
        int area = getArea();
        while (true) {
            System.out.println("1-See the Restaurants NearBy");
            System.out.println("2-Search the Restaurants by Food Type");
            System.out.println("3-Exit");
            int input = GetInput.getIntFromUser(1, 3);
            switch (input) {
                case 1:
                    searchRestaurants(area);
                    continue;
                case 2:
                    searchRestaurantByFoodType(area);
                case 3:
                    System.exit(0);
            }
        }
    }

    private void searchRestaurantByFoodType(int area) {
        FoodType foodType=adminView.getFoodTypeFromUser();
        List<Restaurant> restaurantList=restaurantService.searchRestaurantByFoodType(foodType,area);
        if (restaurantList != null) {
            restaurantList.stream().forEach(System.out::println);
            System.out.println("1-See the Menu Of The Restaurant Based on your custom Type");
            System.out.println("2-Return ");
            int input = GetInput.getIntFromUser(1, 2);
            switch (input) {
                case 1:
                    seeMenuOfRestaurant(restaurantList);
                    break;
                case 2:
                    break;
            }
        } else
            System.out.println("No Restaurants NearBy for your custom Type!Try another Area!");


    }

    private void searchRestaurants(int area) {
        List<Restaurant> restaurants = restaurantService.seeRestaurants(area);
        if (!restaurants.isEmpty()) {
            restaurants.stream().forEach(System.out::println);
            System.out.println("1-See the Menu Of The Restaurant");
            System.out.println("2-Return");
            int input = GetInput.getIntFromUser(1, 2);
            switch (input) {
                case 1:
                    seeMenuOfRestaurant(restaurants);
                    break;
                case 2:
                    break;
            }
        } else
            System.out.println("No Restaurants NearBy!Try another Area!");
    }

    private void seeMenuOfRestaurant(List<Restaurant> restaurants) {
        System.out.println("Enter Name Of Restaurant");
        String nameOfRest = GetInput.getStringFromUser();
        List<Food> foodsOfMenu = foodService.seeMenuOfRestaurant(nameOfRest);
        if (!foodsOfMenu.isEmpty()) {
            foodsOfMenu.stream().forEach(System.out::println);
            System.out.println("1-Order");
            System.out.println("2-Return");
            int input = GetInput.getIntFromUser(1, 2);
            switch (input) {
                case 1:
                    orderFood(foodsOfMenu);
                case 2:
                    break;
            }
        } else if(foodsOfMenu.isEmpty()) {
            System.out.println("No Menu from " + nameOfRest + "!");
        }
    }

    private void orderFood(List<Food> foodsOfMenu) {
        List<Reservation> ordersList = new ArrayList<>();
        addFood:
        while (true) {
            Reservation reservation = addFoodToOrder(foodsOfMenu);
            if (reservation != null)
                ordersList.add(reservation);
            menu:
            while (true) {
                System.out.println("1-Add more food");
                System.out.println("2-Go to Your Shopping Cart");
                System.out.println("3-Return");

                int input = GetInput.getIntFromUser(1, 4);
                switch (input) {
                    case 1:
                        continue addFood;
                    case 2:
                        printShoppingCart(ordersList);
                        continue menu;
                    case 3:
                        break addFood;
                }
            }
        }

    }

    private void printShoppingCart(List<Reservation> reservationList) {
        if (reservationList.isEmpty()) {
            System.out.println("Your shopping cart is EMPTY!");
        } else {
            long totalPrice = 0;
            for (Reservation reservation : reservationList) {
                System.out.println((reservation.getFood().getName() + " " +
                        (reservation.getCount() * reservation.getFood().getPrice())));
                totalPrice += reservation.getCount() * reservation.getFood().getPrice();
            }
            System.out.println(totalPrice);
            checkConfirmEditShoppingCart(reservationList);

        }
    }

    private void checkConfirmEditShoppingCart(List<Reservation> ordersList) {
        confirm:
        while (true) {
            System.out.println("1-Confirm Shopping");
            System.out.println("2-Edit Your Shopping Cart");
            System.out.println("3-Return");
            int input = GetInput.getIntFromUser(1, 3);
            switch (input) {
                case 1:
                    confirmShopping(ordersList);
                    break confirm;
                case 2:
                    editShoppingCart(ordersList);
                    continue confirm;
            }
        }
    }
/********************************* until here **************************/
    private void confirmShopping(List<Reservation> ordersList) {
        Customer customer = customerService.checkUser(phoneNumber);
        if (customer == null) {
            customer = addNewCustomer();
        }
        int factorNumber = reservationService.getMaxFactorNumber();
        for(Reservation reservation :ordersList){
            reservation.setCustomer(customer);
            reservation.setFactorNumber(factorNumber);
            reservation.setDate(new Date());
            reservation.getFood().setCount(reservation.getFood().getCount()-1);
            reservationService.saveOrderToDB(reservation);//save the whole list into the DB
        }
        //empty out cart
        ordersList.clear();
        customerPanel();
    }

    private void editShoppingCart(List<Reservation> ordersList) {
        editShoppingCartWhile:
        while (true) {
            System.out.println("1-delete food");
            System.out.println("2-edit the count of food");
            System.out.println("3-Return");
            int input = GetInput.getIntFromUser(1, 3);
            switch (input) {
                case 1:
                    deleteFoodOrder(ordersList);
                    break;
                case 2:
                    editCountOfFoodOrder(ordersList);
                    break;
                case 3:
                    break editShoppingCartWhile;
            }
        }

    }

    private void editCountOfFoodOrder(List<Reservation> ordersList) {
        if(ordersList.size()>0) {
            System.out.println("Enter name of food");
            String nameOfFood = GetInput.getStringFromUser();
            System.out.println("Enter count of food order");
            int countOfFood=GetInput.getInt();
            for (int i = ordersList.size()-1; i >= 0; i--) {
                if (ordersList.get(i).getFood().getName().equals(nameOfFood)) {
                    ordersList.get(i).setCount(countOfFood);
                    break;
                }
            }

        }
        else{
            System.out.println("Your shopping cart is Empty Now!");
        }
    }

    private void deleteFoodOrder(List<Reservation> ordersList) {

            if(ordersList.size()>0) {
                System.out.println("Enter name of food");
                String nameOfFood = GetInput.getStringFromUser();
                for (int i = ordersList.size()-1; i >= 0; i--) {
                    if (ordersList.get(i).getFood().getName().equals(nameOfFood)) {
                        ordersList.remove(i);
                        break;
                    }
                }

            }
            else{
                System.out.println("Your shopping cart is Empty Now!");
            }

    }

    private Reservation addFoodToOrder(List<Food> foodsOfMenu) {
        int indexOfFood = -1;
        Reservation reservation = new Reservation();
        addFood:
        while (true) {
            indexOfFood = getFoodFromList(foodsOfMenu);
            if (indexOfFood != -1) {
                addNumberOfFoodAgain:
                while (true) {
                    System.out.println("Enter number of food");
                    int countOfFood = GetInput.getInt();
                    if (foodsOfMenu.get(indexOfFood).getCount() >= countOfFood) {
                        reservation.setFood(foodsOfMenu.get(indexOfFood));
                        reservation.setCount(countOfFood);
                        return reservation;
                    } else {
                        if (foodsOfMenu.get(indexOfFood).getCount() == 0) {
                            System.out.println(foodsOfMenu.get(indexOfFood).getName() + " is finished!Try Tomorrow!");
                            return null;
                        }

                        System.out.println("The number of food is less than your order!");
                        System.out.println("1-Add another food");
                        System.out.println("2-Order less or equal to " + foodsOfMenu.get(indexOfFood).getCount());
                        System.out.println("3-return");
                        int input = GetInput.getIntFromUser(1, 3);
                        switch (input) {
                            case 1:
                                continue addFood;
                            case 2:
                                continue addNumberOfFoodAgain;
                            case 3:
                                return null;
                        }
                    }
                }
            } else {
                System.out.println("Wrong Name of Food!Try again!");
                continue;
            }

        }
    }


    private int getFoodFromList(List<Food> foodsOfMenu) {
        System.out.println("Enter name of food");
        String nameOfFood = GetInput.getStringFromUser();
        for (Food food : foodsOfMenu) {
            if (food.getName().equals(nameOfFood)) {
                return foodsOfMenu.indexOf(food);

            }
        }
        return -1;
    }

    private Customer addNewCustomer() {
        System.out.println("Enter Your first name");
        String firstName = GetInput.getStringFromUser();
        System.out.println("Enter your last name ");
        String lastName = GetInput.getStringFromUser();
        System.out.println("Enter your postal code");
        String postalCode = GetInput.getStringFromUser();
        Customer customer = new Customer(firstName, lastName, postalCode, phoneNumber, new Date());
        customerService.addNewCustomer(customer);
        return customer;
    }


    private int getArea() {
        while (true) {
            System.out.println("Enter Your Area");
            int area = GetInput.getInt();
            if (area > 0 && area < 23) {
                return area;
            } else {
                System.out.println("Wrong Area!Try Again");
                continue;
            }
        }
    }

    private String getPhoneNumber() {
        while (true) {
            System.out.println("Enter Your Phone Number");
            String phoneNumber = GetInput.getStringFromUser();
            if (phoneNumber.length() != 11) {
                System.out.println("Your Phone Number's length is less than 11!try again!");
                continue;
            }
            if (!phoneNumber.startsWith("0") && phoneNumber.charAt(1) != '9') {
                System.out.println("Your Phone Number is Invalid");
                continue;
            }
            return phoneNumber;
        }
    }
}
