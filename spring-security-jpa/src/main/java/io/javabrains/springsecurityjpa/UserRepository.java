package io.javabrains.springsecurityjpa;

import org.springframework.data.jpa.repository.JpaRepository;

import io.javabrains.springsecurityjpa.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
}
