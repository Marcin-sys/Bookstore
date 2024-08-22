package pl.mirocha.marcin.it.book.store.services.impl;

import org.springframework.stereotype.Service;
import pl.mirocha.marcin.it.book.store.dao.IPositionDAO;
import pl.mirocha.marcin.it.book.store.model.Position;
import pl.mirocha.marcin.it.book.store.services.IPositionService;

import java.util.Optional;

@Service
public class PositionService implements IPositionService {

    private final IPositionDAO positionDAO;

    public PositionService(IPositionDAO positionDAO) {
        this.positionDAO = positionDAO;
    }

    @Override
    public Optional<Position> getById(int id) {
        return this.positionDAO.getById(id);
    }
}
