package miw.injection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Tests {

    public static void main(String[] args) {
        LocalDateTime date = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);

        System.out.println("===>>>" + date);
    }

}
