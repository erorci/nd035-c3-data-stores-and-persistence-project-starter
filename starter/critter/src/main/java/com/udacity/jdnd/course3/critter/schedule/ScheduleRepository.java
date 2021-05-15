package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT s FROM Schedule s JOIN s.pets AS p JOIN p.owner AS c WHERE c.id = :id")
    List<Schedule> findAllByOwnerId(Long id);

    List<Schedule> findAllByPetsId(Long id);

    List<Schedule> findAllByEmployeesId(Long id);
}
