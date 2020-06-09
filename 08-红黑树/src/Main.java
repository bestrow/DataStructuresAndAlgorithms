import printer.BinaryTrees;
import tree.AVLTree;
import tree.BST;
import tree.BinaryTree;
import tree.RBTree;

public class Main {
    public static void main(String[] args) {
        Integer[] data = new Integer[]{
                100, 45, 68, 93, 94, 74, 60, 84, 50, 29, 58, 44, 78, 96, 37, 40
        };
        RBTree<Integer> rb = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
        }
        BinaryTrees.println(rb);

        for (int i = 0; i < data.length; i++) {
            rb.remove(data[i]);
            System.out.println("-----------------------");
            System.out.println("【"+data[i]+"】");
            BinaryTrees.println(rb);
        }
    }
}
