package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> orders = new LinkedList<>();

    LinkedList<String[]> foods = new LinkedList<>();
    LinkedList<String[]> drinks = new LinkedList<>();

    LinkedList<String[]> isProcessed = new LinkedList<>();

    Queue<String[]> processingOrder = new LinkedList<>();
    Stack<String[]> failedOrder = new Stack<>();

    Scanner scanner = new Scanner(Main.class.getResourceAsStream("orders.txt"));

    while(scanner.hasNext()){
        String[] order = new String[4];
        order[0] = scanner.next();
        order[1] = scanner.next();
        order[2] = scanner.next();
        order[3] = scanner.next();
        orders.add(order);
    }

    scanner.close();

    foods.add(new String[]{"Bakso", "2"});
    foods.add(new String[]{"Sate", "1"});
    foods.add(new String[]{"Soto", "2"});

    drinks.add(new String[]{"EsTeh", "4"});
    drinks.add(new String[]{"EsJeruk", "2"});

    processingOrder.addAll(orders);

    while(!processingOrder.isEmpty()){
        String[] order = processingOrder.poll();

        String foodName = order[1];
        String drinkName = order[2]; 

        boolean foodAvailable = true;
        String[] findFood = null;
        if(!foodName.equals("-")){
            foodAvailable = false;
            for (String[] food : foods){
                if (food[0].equals(foodName)){
                    findFood = food;
                    foodAvailable = Integer.parseInt(food[1]) > 0;
                    break;
                }
            }
        }

        boolean drinkAvailable = true;
        String[] findDrink = null;
        if(!drinkName.equals("-")){
            drinkAvailable = false;
            for (String[] drink : drinks){
                if (drink[0].equals(drinkName)){
                    findDrink = drink;
                    drinkAvailable = Integer.parseInt(drink[1]) > 0;
                    break;
                }
            }
        }

        if (foodAvailable && drinkAvailable){
            if (findFood != null){
                findFood[1] = String.valueOf(Integer.parseInt(findFood[1]) - 1);
            }
            if (findDrink != null){
                findDrink[1] = String.valueOf(Integer.parseInt(findDrink[1]) - 1);
            }
            isProcessed.add(order);
        } else {
            failedOrder.push(order);
        }
    }

    System.out.println("=== Processed Orders ===");
    for (String[] order : isProcessed){
        System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
    }
    System.out.println();

        System.out.println("=== Remaining Food Stock ===");
        for (String[] food : foods){
            System.out.println(food[0] + " : " + food[1]);
        }
        System.out.println();

        System.out.println("=== Remaining Drink Stock ===");
        for (String[] drink : drinks){
            System.out.println(drink[0] + " : " + drink[1]);
        }
        System.out.println();

        System.out.println("=== Failed Orders ===");
        while (!failedOrder.isEmpty()){
            String[] order = failedOrder.pop();
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }
    }
}