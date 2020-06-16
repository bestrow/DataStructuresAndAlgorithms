import map.Map;
import map.Map.Visitor;
import map.TreeMap;
import set.Set;
import set.TreeSet;

public class Main {
    public static void main(String[] args) {
        /*Map<String, Integer> map = new TreeMap<>();
        map.put("c", 2);
        map.put("a", 5);
        map.put("b", 6);
        map.put("a", 8);

        map.traversal(new Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });*/

        Set<String> set = new TreeSet<>();
        set.add("c");
        set.add("b");
        set.add("c");
        set.add("c");
        set.add("a");
        set.traversal(new Set.Visitor<String>() {
            @Override
            public boolean visit(String element) {
                System.out.println(element);
                return false;
            }
        });
    }
}
