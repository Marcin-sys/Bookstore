package pl.mirocha.marcin.it.book.store.dao.hibernate;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.mirocha.marcin.it.book.store.dao.IPositionDAO;
import pl.mirocha.marcin.it.book.store.model.Position;

import java.util.List;
import java.util.Optional;
import java.util.Queue;
@Repository
public class PositionDAO implements IPositionDAO {

    private final SessionFactory sessionFactory;

    private final String GET_BY_ID_HQL = "FROM pl.mirocha.marcin.it.book.store.model.Position WHERE id = :id";

    public PositionDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Position> getByOrderId(int OrderId) {
        throw  new UnsupportedOperationException();
    }

    @Override
    public void save(Position position, int orderId) {
        throw  new UnsupportedOperationException();

    }

    @Override
    public void update(Position position) {
        throw  new UnsupportedOperationException();

    }

    @Override
    public void delete(int positionId) {
        throw  new UnsupportedOperationException();

    }

    @Override
    public Optional<Position> getById(int id) {
        Session session = this.sessionFactory.openSession();
        Query <Position> query = session.createQuery(GET_BY_ID_HQL, Position.class);
        query.setParameter("id",id);

        try {
            return Optional.of(query.getSingleResult());
        }catch (NoResultException e){
            return Optional.empty();
        }finally {
            session.close();
        }
    }
}
