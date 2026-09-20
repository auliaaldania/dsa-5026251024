package lw01.prelab;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args){
        List<PrintJob> jobs = new ArrayList<>();

        File file = new File("src/lw01/prelab/jobs.txt");

        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNext()){
                String type = scanner.next();
                String id = scanner.next();
                int pages = scanner.nextInt();

                if (type.equalsIgnoreCase("MONO")){
                    jobs.add(new MonoPrint(id, pages));
                } else if (type.equalsIgnoreCase("COLOUR")){
                    jobs.add(new ColourPrint(id, pages));
                }
            }
        } catch (FileNotFoundException e){
            System.err.println("File not found: " + e.getMessage());
            return;
        }

        for (PrintJob job : jobs){
            System.out.println(job.summary());
        }
    }
}
