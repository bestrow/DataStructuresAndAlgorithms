import printer.BinaryTrees;
import tree.AVLTree;
import tree.BST;

public class Main {
    public static void main(String[] args) {
        Integer[] data = new Integer[]{
                100, 45, 68, 93, 94, 74, 60, 84, 50, 29, 58, 44, 78, 96, 37, 40
        };
        AVLTree<Integer> bst = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);
    }
}
