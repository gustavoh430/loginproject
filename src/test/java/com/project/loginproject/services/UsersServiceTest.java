package com.project.loginproject.services;

import com.project.loginproject.entities.Role;
import com.project.loginproject.entities.Users;
import com.project.loginproject.repositories.RolesRepository;
import com.project.loginproject.repositories.UsersRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

class UsersServiceTest {

    UsersRepository userRepository;

    UsersService usersService;

    @Test
    @DisplayName("Should return false for empty fields")
    void nonNullFields() {
        List<Role> role = new ArrayList();
        Role role1 = new Role(1, "adm");
        role.add(role1);
        Users users = new Users(1L, "Gustavo", "17", "teste@gmail.com", "123", "123", role);
        Assertions.assertThat(users.getEmail().isEmpty()).isFalse();
        Assertions.assertThat(users.getName().isEmpty()).isFalse();
        Assertions.assertThat(users.getPassword().isEmpty()).isFalse();
        Assertions.assertThat(users.getAge().isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Should return True for empty fields")
    void nonNullFields2() {
        List<Role> role = new ArrayList();
        Role role1 = new Role(1, "adm");
        role.add(role1);
        Users users = new Users(1L, "", "", "", "", "", role);
        Assertions.assertThat(users.getEmail().isEmpty()).isTrue();
        Assertions.assertThat(users.getName().isEmpty()).isTrue();
        Assertions.assertThat(users.getPassword().isEmpty()).isTrue();
        Assertions.assertThat(users.getAge().isEmpty()).isTrue();
    }

}
