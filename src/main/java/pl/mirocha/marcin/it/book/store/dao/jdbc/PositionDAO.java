package pl.mirocha.marcin.it.book.store.dao.jdbc;

import pl.mirocha.marcin.it.book.store.dao.IBookDAO;
import pl.mirocha.marcin.it.book.store.dao.IPositionDAO;
import pl.mirocha.marcin.it.book.store.model.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PositionDAO implements IPositionDAO {


    private final Connection connection;
    private final IBookDAO iBookDAO;

    public PositionDAO(Connection connection, IBookDAO iBookDAO) {
        this.connection = connection;
        this.iBookDAO = iBookDAO;
    }

    @Override
    public List<Position> getByOrderId(int orderId) {
        List<Position> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tposition WHERE order_id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1,orderId);

            ResultSet rs =preparedStatement.executeQuery();
            while (rs.next()){
                result.add(new Position(
                        rs.getInt("id"),
                        this.iBookDAO.getById(rs.getInt("book_id")).get(),
                        rs.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            System.out.println("POSITION get by order id error");
        }
        return result;
    }

    @Override
    public void save(Position position, int orderId) {
        try {
            String sql = "INSERT INTO tposition (book_id, quantity, order_id) VALUES (?,?,?);";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1,position.getBook().getId());
            preparedStatement.setInt(2,position.getQuantity());
            preparedStatement.setInt(3,orderId );

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("save position error");
        }
    }

    @Override
    public void update(Position position) {
        try {
            String sql = "UPDATE tpoisition SET book_id =? , qunatity => WHERE id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1,position.getBook().getId());
            preparedStatement.setInt(2,position.getQuantity());
            preparedStatement.setInt(3,position.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error position update");
        }
    }

    @Override
    public void delete(int positionId) {
        try {
            String sql = "DELETE FROM tposition WHERE id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1,positionId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("delete position error");
        }
    }

    @Override
    public Optional<Position> getById(int id) {
        return Optional.empty();
    }
}
