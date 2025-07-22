package com.example.Smart_helmet.repository;

import com.example.Smart_helmet.model.UserInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepository_saveuserTest(){

    //arrange
        UserInfo user = new UserInfo();
        user.setEmail("test@email.com");
        user.setFullname("test name");
        user.setUuid("123456");
        user.setGender("male");
        user.setDateofbirth(LocalDate.of(2004, 12, 6));
        user.setPassword("securePassword123");
        //act
        UserInfo savedUser = userRepository.save(user);

        //assert
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getFullname()).isEqualTo("test name");
        assertThat(savedUser.getEmail()).isEqualTo("test@email.com");
        assertThat(savedUser.getUuid()).isEqualTo("123456");
        assertThat(savedUser.getGender()).isEqualTo("male");
        assertThat(savedUser.getDateofbirth()).isEqualTo(LocalDate.of(2004, 12, 6));
        assertThat(savedUser.getPassword()).isEqualTo("securePassword123");

    }

}
