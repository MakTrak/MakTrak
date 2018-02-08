package com.codeup.maktrak.daos;

import com.codeup.maktrak.models.DailyMacro;
import com.codeup.maktrak.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DailyMacroRepository extends CrudRepository<DailyMacro, Long> {
    List<DailyMacro> findByOwner(User user);
}
