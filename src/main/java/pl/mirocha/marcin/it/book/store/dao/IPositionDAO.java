package pl.mirocha.marcin.it.book.store.dao;

import pl.mirocha.marcin.it.book.store.exceptions.UserNotExistException;
import pl.mirocha.marcin.it.book.store.model.Position;

import java.util.List;
import java.util.Optional;

public interface IPositionDAO {
    List<Position> getByOrderId (int OrderId);
    void save (Position position, int orderId);
    void update (Position position);
    void delete(int positionId);

    default Optional<Position> getById(int id){
        throw new UserNotExistException();
    }

}
