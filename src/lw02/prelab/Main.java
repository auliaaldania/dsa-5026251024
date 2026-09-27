package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactionList = new LinkedList<>();
        LinkedList<String[]> customerList = new LinkedList<>();
        Queue<String[]> transactionQueue = new LinkedList<>();
        Stack<String[]> failedStack = new Stack<>();

        Scanner sc = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        while (sc.hasNext()){
            String name = sc.next();
            String type = sc.next();
            String amount = sc.next();
            transactionList.add(new String[] {name, type, amount});
        }

        for (String[] t : transactionList){
            String name = t[0];
            if (findCustomer(customerList, name) == null){
                customerList.add(new String[] {name, "0"});
            }
        }

        transactionQueue.addAll(transactionList);

        while (!transactionQueue.isEmpty()){
            String[] t = transactionQueue.poll();
            String name = t[0];
            String type = t[1];
            int amount = Integer.parseInt(t[2]);

            String[] customer = findCustomer(customerList, name);
            int balance = Integer.parseInt(customer[1]);

            if (type.equals("DEPOSIT")){
                balance = balance + amount;
                customer[1] = String.valueOf(balance);
            } else if (type.equals("WITHDRAW")){
                if (amount > balance){
                    failedStack.push(t);
                } else{
                    balance = balance - amount;
                    customer[1] = String.valueOf(balance);
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] customer : customerList){
            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");
        while (!failedStack.isEmpty()){
            String[] t = failedStack.pop();
            System.out.println(t[0] + " " + t[1] + " " + t[2]);
        }

        sc.close();
    }

    private static String[] findCustomer(LinkedList<String[]> customerList, String name){
        for (String[] customer : customerList){
            if (customer[0].equals(name)){
                return customer;
            }
        }
        return null;
    }
}
