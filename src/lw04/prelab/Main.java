package lw04.prelab;

import java.util.Scanner;

public class Main {
    static void insertion(int[] a, int passes){
        for (int i = 1; i <= passes && i < a.length; i++){
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] < key){
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    static int linear(int[] a, int target){
        for (int i = 0; i < a.length; i++){
            if (a[i] == target){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(Main.class.getResourceAsStream("branches.txt"));
        
        int n = sc.nextInt();
        int[] branches = new int[n];
        for (int i = 0; i < n; i++){
            branches[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++){
            targets[i] = sc.nextInt();
        }
        sc.close();

        insertion(branches, 3);

        for (int i = 0; i < n; i++){
            System.out.print(branches[i]);
            if (i < n - 1){
                System.out.print(" ");
            }
        }
        System.out.println();

        for (int i = 0; i < m; i++){
            int index = linear(branches, targets[i]);
            if (index >= 0){
                System.out.println(targets[i] + ": FOUND at index " + index);
            } else{
                System.out.println(targets[i] + ": NOT FOUND");
            }
        }
    }
}
