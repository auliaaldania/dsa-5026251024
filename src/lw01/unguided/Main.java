package lw01.unguided;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("washes.txt"));
        ArrayList<WashService> washes = new ArrayList<WashService>();

        while (scanner.hasNext()){
            // int input = scanner.nextInt();
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            int units = scanner.nextInt();

            if (type.equals("MOTORCYCLE")){
                washes.add(new MotorcycleWash(id, days));
            } else if (type.equals("CAR")){
                washes.add(new CarWash(id, days, units));
            }
        }

        scanner.close();

        for (WashService wash : washes){
            System.out.println(wash.summary());
        }
    }
   
}
