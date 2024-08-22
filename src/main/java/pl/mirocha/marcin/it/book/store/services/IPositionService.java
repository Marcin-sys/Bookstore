package pl.mirocha.marcin.it.book.store.services;

import pl.mirocha.marcin.it.book.store.model.Position;

import java.util.Optional;

public interface IPositionService {
    Optional<Position> getById(int id);
}
