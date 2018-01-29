package com.codeup.maktrak.daos;

import com.codeup.maktrak.models.DailyMacro;
import org.springframework.data.repository.CrudRepository;

public interface DailyMacroRepository extends CrudRepository<DailyMacro, Long> {
}
