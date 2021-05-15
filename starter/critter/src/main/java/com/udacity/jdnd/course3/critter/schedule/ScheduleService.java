package com.udacity.jdnd.course3.critter.schedule;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findAll() {
        return Lists.newArrayList(scheduleRepository.findAll());
    }

    public List<Schedule> findAllByPetsId(Long id) {
        return scheduleRepository.findAllByPetsId(id);
    }

    public List<Schedule> findAllByEmployeesId(Long id) {
        return scheduleRepository.findAllByEmployeesId(id);
    }

    public List<Schedule> findAllByOwnerId(Long id) {
        return scheduleRepository.findAllByOwnerId(id);
    }
}
