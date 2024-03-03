package pl.mirocha.marcin.it.book.store.dao.memory;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/*@Repository
@Scope("prototype")*/
public class IdSequence {
    private int id = 0;

    public int getId() {
        return id++;
    }
}
