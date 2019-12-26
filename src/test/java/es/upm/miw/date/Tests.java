package es.upm.miw.date;

import org.apache.logging.log4j.LogManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Tests {

    public static void main(String[] args) {
        LogManager.getLogger().debug("===>>>" + LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
        LogManager.getLogger().debug("===>>>" + LocalDate.now().getDayOfWeek().toString());
    }

}
