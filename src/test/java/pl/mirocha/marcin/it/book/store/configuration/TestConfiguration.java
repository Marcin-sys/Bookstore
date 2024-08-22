package pl.mirocha.marcin.it.book.store.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.mirocha.marcin.it.book.store.dao.*;

@Configuration
@ComponentScan(basePackages = {
        "pl.mirocha.marcin.it.book.store.controllers",
        "pl.mirocha.marcin.it.book.store.services"
})
public class TestConfiguration {
/*
    @Bean
    public IBookDAO bookDAO(){
//        return new BookDAOStub();
        return Mockito.mock(IBookDAO.class);
    }

    @Bean
    public IPositionDAO  positionDAO(){
//        return new PositionDAOStub();
        return Mockito.mock(IPositionDAO.class);

    }

    @Bean
    public IUserDAO userDAO(){
//        return new UserDAOStub();
        return Mockito.mock(IUserDAO.class);

    }

    @Bean
    public IOrderDAO orderDAO(){
//        return new OrderDAOStub();
        return Mockito.mock(IOrderDAO.class);*/
//    }
}
