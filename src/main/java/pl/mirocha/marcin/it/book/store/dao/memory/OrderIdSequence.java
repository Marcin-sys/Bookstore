package pl.mirocha.marcin.it.book.store.dao.memory;

import org.springframework.stereotype.Repository;

@Repository
public class OrderIdSequence {
    private int id = 0;

    public int getId() {
        return id++;
    }
}
