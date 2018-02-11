package com.codeup.maktrak.daos;

import com.codeup.maktrak.models.User;
import com.codeup.maktrak.models.WeeklySchedule;
import org.springframework.data.repository.CrudRepository;

public interface WeeklyScheduleRepository extends CrudRepository<WeeklySchedule, Long> {
    WeeklySchedule findByOwner(User user);
}
