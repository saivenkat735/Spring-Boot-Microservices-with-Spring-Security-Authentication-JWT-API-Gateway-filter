package com.ust.AuthenticationService.dao;

import com.ust.AuthenticationService.entity.UserCredentialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialsDao extends JpaRepository<UserCredentialsEntity,Integer> {
    public Optional<UserCredentialsEntity> findByName(String name);
}
