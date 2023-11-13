package com.project.loginproject.repositories;

import com.project.loginproject.entities.Role;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"test"})
class RolesRepositoryTest {
    @Autowired
    EntityManager entityManager;
    @Autowired
    RolesRepository rolesRepository;

    RolesRepositoryTest() {
    }

    @Transactional
    @Test
    @DisplayName("Shloud get Roles from DB successfully")
    void findRoleByName() {
        Role data = new Role(1, "ADMIN");
        this.CreateRole(data);
        Optional<Role> foundedRole = this.rolesRepository.findByName(data.getName());
        Assertions.assertThat(foundedRole.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Shloud not get Roles from DB successfully")
    void findRoleByName2() {
        Role data = new Role(1, "ADM");
        Optional<Role> foundedRole = this.rolesRepository.findByName(data.getName());
        Assertions.assertThat(foundedRole.isEmpty()).isTrue();
    }

    private Role CreateRole(Role data) {
        Set<Role> role = new HashSet();
        Role role1 = new Role(data.getId(), data.getName());
        role.add(role1);
        this.rolesRepository.save(role1);
        return role1;
    }
}