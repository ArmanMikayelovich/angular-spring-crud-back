package com.energizeglobal.com.internship.angular;

import com.energizeglobal.com.internship.angular.model.UserEntity;
import com.energizeglobal.com.internship.angular.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
@SpringBootApplication
@Transactional
public class App implements CommandLineRunner {

    private final UserRepository userRepository;

    public App(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {

        SpringApplication.run(App.class);
    }

    @Override
    public void run(String... args) throws Exception {
        List<UserEntity>  usersToSave= new ArrayList<>();
        for (int x = 0; x < 10; x++) {
            final UserEntity userEntity = new UserEntity();
            userEntity.setBirthday(LocalDate.now().minusMonths(x).minusDays(x));
            userEntity.setEmail(x + "@gmail.com");
            userEntity.setFirstName(x + " first");
            userEntity.setLastName(x + " last");
            usersToSave.add(userEntity);
        }
        userRepository.saveAll(usersToSave);
        userRepository.flush();
    }
}
