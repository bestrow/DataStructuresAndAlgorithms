import set.Set;
import set.TreeSet;

public class Main {
    public static void main(String[] args) {

        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(10);
        treeSet.add(9);
        treeSet.add(9);
        treeSet.add(11);
        treeSet.add(10);

        treeSet.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }
}
