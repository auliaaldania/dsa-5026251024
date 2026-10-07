package lw03.unguided;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args){
        Scanner registrations = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        Scanner checkins = new Scanner(Main.class.getResourceAsStream("checkins.txt"));

        Set<String> registeredParticipants = new LinkedHashSet<>();
        while (registrations.hasNext()){
            String id = registrations.nextLine();
            registeredParticipants.add(id);
        }
        registrations.close();

        Set<String> checkedInParticipants = new LinkedHashSet<>();
        List<String> alreadyCheckins = new ArrayList<>();
        int rejected = 0;
        while (checkins.hasNext()){
            String id = checkins.next();
            if (!registeredParticipants.contains(id)) {
                alreadyCheckins.add(id + ": Rejected (not registered)");
                rejected++;
            } else if (checkedInParticipants.contains(id)){
                alreadyCheckins.add(id + ":Rejected (already checked in)");
                rejected++;
            } else {
                checkedInParticipants.add(id);
                alreadyCheckins.add(id + ": Checked in");
            }
        }
        checkins.close();

        System.out.println("===== Event Check-In Results =====");
        for (int i = 0; i < alreadyCheckins.size(); i++){
            System.out.println(alreadyCheckins.get(i));
        }

        System.out.println();
        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registeredParticipants.size());
        System.out.println("Successful check-ins: " + checkedInParticipants.size());
        System.out.println("Absent students: " + (registeredParticipants.size() - checkedInParticipants.size()));
        System.out.println("Rejected attempts: " + rejected);

    }
}
