package pl.mirocha.marcin.it.book.store.dao.jdbc;

import org.springframework.stereotype.Repository;
import pl.mirocha.marcin.it.book.store.dao.IOrderDAO;
import pl.mirocha.marcin.it.book.store.dao.IPositionDAO;
import pl.mirocha.marcin.it.book.store.dao.IUserDAO;
import pl.mirocha.marcin.it.book.store.model.Order;
import pl.mirocha.marcin.it.book.store.model.Position;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class OrderDAO implements IOrderDAO {

    private final Connection connection;
    private final IUserDAO userDAO;
    private final IPositionDAO positionDAO;

    public OrderDAO(Connection connection, IUserDAO userDAO, IPositionDAO positionDAO) {
        this.connection = connection;
        this.userDAO = userDAO;
        this.positionDAO = positionDAO;
    }

    @Override
    public Optional<Order> getById(int id) {
        try {
            String sql = "SELECT * FROM torder WHERE id=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setTotal(rs.getInt("total"));
                order.setStatus(Order.Status.valueOf(rs.getString("status")));
                order.setDateTime(rs.getTimestamp("date").toLocalDateTime());
                order.setUser(this.userDAO.getById(rs.getInt("user_id")).get());
                order.getPositions().addAll(this.positionDAO.getByOrderId(order.getId()));
                return Optional.of(order);
            }
        } catch (SQLException e) {
            System.out.println("order get by id  error");
            ;
        }
        return Optional.empty();
    }

    @Override
    public List<Order> getAll() {
        List<Order> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM torder";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setTotal(rs.getInt("total"));
                order.setStatus(Order.Status.valueOf(rs.getString("status")));
                order.setDateTime(rs.getTimestamp("date").toLocalDateTime());
                order.setUser(this.userDAO.getById(rs.getInt("user_id")).get());
                order.getPositions().addAll(this.positionDAO.getByOrderId(order.getId()));
                result.add(order);
            }
        } catch (SQLException e) {
            System.out.println("order get by id  error");
            ;
        }
        return result;
    }

    @Override
    public void save(Order order) {
        try {
            String sql = "INSERT INTO torder (status,total,user_id) VALUES (?,?,?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, order.getStatus().name());
            preparedStatement.setDouble(2, order.getTotal());
            preparedStatement.setInt(3, order.getUser().getId());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            order.setId(rs.getInt(1));

            //wyciaganie daty
            String dateSql = "SELECT date FROM torder WHERE id = ?";
            PreparedStatement preparedStatementDate = this.connection.prepareStatement(dateSql);
            preparedStatementDate.setInt(1, order.getId());

            ResultSet dateResult = preparedStatementDate.executeQuery();
            dateResult.next();
            order.setDateTime(dateResult.getTimestamp("date").toLocalDateTime());
            //koniec wyciagania daty

            for (Position position : order.getPositions()) {
                this.positionDAO.save(position, order.getId());
            }


        } catch (SQLException e) {
            System.out.println("save order error");
        }
    }

    @Override
    public void update(Order order) {
        try {
            String sql = "UPDATE torder SET date =? ,status = ? , total =? user_id=? WHERE id = ? ";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(order.getDateTime()));
            preparedStatement.setString(2, order.getStatus().name());
            preparedStatement.setDouble(3, order.getTotal());
            preparedStatement.setInt(4, order.getUser().getId());
            preparedStatement.setInt(5, order.getId());

        } catch (SQLException e) {
            System.out.println("order update error");
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM torder WHERE id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();

            List<Position> positions = this.positionDAO.getByOrderId(id);
            for (Position position : positions){
                this.positionDAO.delete(position.getId());
            }
        } catch (SQLException e) {
            System.out.println("order delete error");
        }
    }

    @Override
    public List<Order> getByUserId(int userId) {
        List<Order > result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM torder WHERE user_id = ? ";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setTotal(rs.getInt("total"));
                order.setStatus(Order.Status.valueOf(rs.getString("status")));
                order.setDateTime(rs.getTimestamp("date").toLocalDateTime());
                order.setUser(this.userDAO.getById(rs.getInt("user_id")).get());
                order.getPositions().addAll(this.positionDAO.getByOrderId(order.getId()));
                result.add(order);
            }

        }catch (SQLException e) {
            System.out.println("getbyuser id order error");
        }
        return result;
    }
}
