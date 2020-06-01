import printer.BinaryTrees;
import tree.BST;

public class Main {
    public static void main(String[] args) {
        Integer[] data = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3
        };
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);

        bst.preorderTraversal();
    }
}
