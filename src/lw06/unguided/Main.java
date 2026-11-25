package lw06.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        String hai = read.nextLine();

        BinarySearchTree bst = new BinarySearchTree();

        String[] numbers = hai.split(" ");
        for (int i = 0; i < numbers.length; i++) {
            bst.insert(Integer.parseInt(numbers[i]));
        }

        System.out.print("Preorder : ");
        bst.preOrder();

        System.out.print("Inorder : ");
        bst.inOrder();

        System.out.print("Postorder : ");
        bst.postOrder();

        System.out.println("Search 65 : " + bst.search(65));
        System.out.println("Nodes : " + bst.countNodes());
        System.out.println("Leaves : " + bst.countLeaves());

        read.close();
    }
}