package pl.mirocha.marcin.it.book.store.dao.jdbc;

import org.springframework.stereotype.Repository;
import pl.mirocha.marcin.it.book.store.dao.IUserDAO;
import pl.mirocha.marcin.it.book.store.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAO implements IUserDAO {

    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> getById(int id) {
        try {
            String sql = "SELECT * FROM tuser WHERE id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                return Optional.of( new User(
                       rs.getInt("id"),
                       rs.getString("login"),
                       rs.getString("password"),
                       rs.getString("name"),
                       rs.getString("surname"),
                       User.Role.valueOf(rs.getString("role"))
                ));
            }
        }catch (SQLException e){
            System.out.println("getting user by Id error");
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getByLogin(String login) {
        try {
            String sql = "SELECT * FROM tuser WHERE login = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1,login);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                return Optional.of( new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        User.Role.valueOf(rs.getString("role"))
                ));
            }
        }catch (SQLException e){
            System.out.println("getting user by Id error");
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tuser;";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                 result.add(new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        User.Role.valueOf(rs.getString("role"))
                 ));
            }
        } catch (SQLException e) {
            System.out.println("error in get all users");;
        }
        return result;
    }

    @Override
    public void save(User user) {
        try {
            String sql = "INSERT INTO tuser (login,password,name,surname,role) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement
                    (sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getName());
            preparedStatement.setString(4,user.getSurname());
            preparedStatement.setString(5,user.getRole().toString());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt(1));

        } catch (SQLException e) {
            System.out.println("Error with save user");
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM tuser WHERE id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error with deleting user");
        }

    }

    @Override
    public void update(User user) {
        try {
            String sql = "UPDATE tuser SET login =? , password = ?, name =?, surname=?, role=? WHERE id =?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getName());
            preparedStatement.setString(4,user.getSurname());
            preparedStatement.setString(5,user.getRole().toString());
            preparedStatement.setInt(6,user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("update user error!");
        }
    }
}
