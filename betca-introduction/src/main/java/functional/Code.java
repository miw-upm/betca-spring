package functional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Code {
    public static final List<Integer> INTEGER_LIST = List.of(3, -2, 0, 4);
    public static final List<String> STRING_LIST = List.of("3", "-2", "0", "4");


    public void consumer() {
        for (int i = 0; i < INTEGER_LIST.size(); i++) { //for i
            System.out.println(INTEGER_LIST.get(i));
        }
        for (int item : INTEGER_LIST) {  //for each
            System.out.println(item);
        }
        INTEGER_LIST.stream()
                .forEach(System.out::println);  //functional
    }

    public void predicate() {
        for (int i = 0; i < INTEGER_LIST.size(); i++) { //for i
            if (INTEGER_LIST.get(i) >= 0) {
                System.out.println(INTEGER_LIST.get(i));
            }
        }
        for (int item : INTEGER_LIST) {  //for each
            if (item >= 0) {
                System.out.println(item);
            }
        }
        INTEGER_LIST.stream() //functional
                .filter(item -> item >= 0)
                .forEach(System.out::println);
    }

    public void function() {
        List<Integer> result2 = new ArrayList<>();
        for (String item : STRING_LIST) {  //for each
            int intItem = Integer.valueOf(item);
            if (intItem != 0) {
                result2.add(intItem * 2);
            }
        }
        System.out.println(result2);

        List<Integer> result = STRING_LIST.stream() //functional
                .map(Integer::valueOf)
                .filter(item -> item != 0)
                .map(item -> item * 2)
                .collect(Collectors.toList());
        System.out.println(result);
    }

}
