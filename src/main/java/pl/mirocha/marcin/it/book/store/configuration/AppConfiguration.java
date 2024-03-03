package pl.mirocha.marcin.it.book.store.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.*;

@Configuration
@ComponentScan("pl.mirocha.marcin.it.book.store")
public class AppConfiguration {
    @Bean
    public Connection connection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
    }
}
