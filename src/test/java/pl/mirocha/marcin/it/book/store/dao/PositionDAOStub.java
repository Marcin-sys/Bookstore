package pl.mirocha.marcin.it.book.store.dao;

import pl.mirocha.marcin.it.book.store.model.Position;

import java.util.List;

public class PositionDAOStub implements IPositionDAO{
    @Override
    public List<Position> getByOrderId(int OrderId) {
        return null;
    }

    @Override
    public void save(Position position, int orderId) {

    }

    @Override
    public void update(Position position) {

    }

    @Override
    public void delete(int positionId) {

    }
}
