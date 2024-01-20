package pl.mirocha.marcin.it.book.store.dao.memory;

import org.springframework.stereotype.Component;

@Component
public class UserIdSequence {
    private int id = 0;
    public int getId() {
        return id++;
    }
}
