package pl.mirocha.marcin.it.book.store.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StringGenerator {


    @Scheduled(cron = "0/30 * * ? * *")
    public void generateString(){
        System.out.println("jakas informacja");
        System.out.println(LocalDateTime.now());
    }
}
