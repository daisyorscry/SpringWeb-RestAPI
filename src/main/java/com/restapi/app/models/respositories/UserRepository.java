package com.restapi.app.models.respositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapi.app.models.entitiies.User;


@Repository
public interface UserRepository extends JpaRepository<User, String>
{
    Optional<User> findByToken(String token);
}