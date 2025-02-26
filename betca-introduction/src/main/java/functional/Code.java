package functional;

import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

public class Code {
    public static final List<Integer> INTEGER_LIST = List.of(3, -2, 0, 4);
    public static final List<String> STRING_LIST = List.of("3", "-2", "0", "4");


    public void consumer() {
        for (int i = 0; i < INTEGER_LIST.size(); i++) { //for i
            LogManager.getLogger(this.getClass()).info(INTEGER_LIST.get(i));
        }
        for (int item : INTEGER_LIST) {  //for each
            LogManager.getLogger(this.getClass()).info(item);
        }
        INTEGER_LIST
                .forEach(item -> LogManager.getLogger(this.getClass()).info(item));
    }

    public void predicate() { // only positive values
        for (int item : INTEGER_LIST) {
            if (item >= 0) {
                LogManager.getLogger(this.getClass()).info(item);
            }
        }
        INTEGER_LIST.stream() //functional
                .filter(item -> item >= 0)
                .forEach(item -> LogManager.getLogger(this.getClass()).info(item));
    }

    public void function() { // convert to int, *2 & remove 0
        List<Integer> result2 = new ArrayList<>();
        for (String item : STRING_LIST) {  //for each
            int intItem = Integer.parseInt(item);
            if (intItem != 0) {
                result2.add(intItem * 2);
            }
        }
        LogManager.getLogger(this.getClass()).info(result2);

        List<Integer> result = STRING_LIST.stream() //functional
                .map(Integer::valueOf)
                .filter(item -> item != 0)
                .map(item -> item * 2)
                .toList();
        LogManager.getLogger(this.getClass()).info(result);
    }

}
