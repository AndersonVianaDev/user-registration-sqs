package com.anderson.msuser.core.user.repository;

import com.anderson.msuser.core.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);
    Optional<User> findByEmail(String email);
    List<User> findAll(Integer page, Integer size);
}
