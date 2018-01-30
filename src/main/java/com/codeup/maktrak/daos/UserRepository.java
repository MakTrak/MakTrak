package com.codeup.maktrak.daos;

import com.codeup.maktrak.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
