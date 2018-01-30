package com.codeup.maktrak.daos;

import com.codeup.maktrak.models.User;
import org.springframework.data.repository.CrudRepository;

public interface Users extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
