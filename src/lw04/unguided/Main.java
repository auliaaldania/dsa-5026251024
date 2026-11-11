package lw04.unguided;

import java.util.Scanner;

public class Main {
    static void bubbleSort(int[] a, int pass){
        int n = a.length;
        for (int j = 0; j < n - 1 - pass; j++){
            if (a[j] < a[j + 1]){
                int temp = a[j];
                a[j] = a[j + 1];
                a[j + 1] = temp;
            }
        }
    }

    static int binarySearch(int[] a, int target, int[] steps){
        int low = 0;
        int high = a.length - 1;
        steps[0] = 0;
        while (low <= high){
            int mid = (low + high) / 2;
            steps[0]++;
            if (a[mid] == target){
                return mid;
            } else if (a[mid] < target){
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    static void printArray(int[] a){
        for (int i = 0; i < a.length; i++){
            System.out.print(a[i]);
            if (i < a.length - 1){
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(Main.class.getResourceAsStream("parcels.txt"));

        int n = sc.nextInt();
        int[]parcels = new int[n];
        for (int i = 0; i < n; i++){
            parcels[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++){
            targets[i] = sc.nextInt();
        }
        sc.close();

        for (int pass = 0; pass < n - 1; pass++){
            bubbleSort(parcels, pass);
        }
        int[] afterTwoPasses = new int[n];
        for (int i = 0; i < n; i++){
            afterTwoPasses[i] = parcels[i];
        }

        for (int pass = 2; pass < n - 1; pass++){
            bubbleSort(parcels, pass);
        }

        System.out.println("==== After 2 Passes ====");
        printArray(afterTwoPasses);
        System.out.println("==== Fully Sorted ====");
        printArray(parcels);

        System.out.println("==== Search Results ====");
        int[] steps = new int[1];
        for (int i = 0; i < m; i++){
            int index = binarySearch(parcels, targets[i], steps);
            if (index >= 0){
                System.out.println(targets[i] + ": FOUND at index " + index + " (steps: " + steps[0] + ")");
            } else{
                System.out.println(targets[i] + ": NOT FOUND (steps: " + steps[0] + ")");
            }
        }
    }
}
