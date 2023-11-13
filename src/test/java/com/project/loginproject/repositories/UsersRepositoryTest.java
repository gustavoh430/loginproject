package com.project.loginproject.repositories;

import com.project.loginproject.entities.Role;
import com.project.loginproject.entities.Users;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"test_2"})
class UsersRepositoryTest {
    @Autowired
    UsersRepository userRepository;

    UsersRepositoryTest() {
    }

    @Test
    @DisplayName("Shloud get Users from DB successfully")
    void findUserByEmail() {
        List<Role> role = new ArrayList();
        Role role1 = new Role(1, "adm");
        role.add(role1);
        Users users = new Users(1L, "Gustavo", "17", "teste@gmail.com", "123", "123", role);
        this.CreateUser(users);
        Optional<Users> foundedUser = Optional.ofNullable(this.userRepository.findByEmail(users.getEmail()));
        Assertions.assertThat(foundedUser.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Shloud not get Users from DB successfully")
    void findUserByEmail2() {
        List<Role> role = new ArrayList();
        Role role1 = new Role(1, "adm");
        role.add(role1);
        Users users = new Users(1L, "Gustavo", "17", "teste@gmail.com", "123", "123", role);
        Optional<Users> foundedUser = Optional.ofNullable(this.userRepository.findByEmail(users.getEmail()));
        Assertions.assertThat(foundedUser.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Shloud get Users from DB successfully")
    void findUserByPassword() {
        List<Role> role = new ArrayList();
        Role role1 = new Role(1, "adm");
        role.add(role1);
        Users users = new Users(1L, "Gustavo", "17", "teste@gmail.com", "123", "123", role);
        this.CreateUser(users);
        Optional<Users> foundedUser = Optional.ofNullable(this.userRepository.findByPassword(users.getPassword()));
        Assertions.assertThat(foundedUser.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Shloud not get Users from DB successfully")
    void findUserByPassword2() {
        List<Role> role = new ArrayList();
        Role role1 = new Role(1, "adm");
        role.add(role1);
        Users users = new Users(1L, "Gustavo", "17", "teste@gmail.com", "1234", "1234", role);
        Optional<Users> foundedUser = Optional.ofNullable(this.userRepository.findByPassword(users.getPassword()));
        Assertions.assertThat(foundedUser.isEmpty()).isTrue();
    }

    private Users CreateUser(Users users) {
        this.userRepository.save(users);
        return users;
    }
}