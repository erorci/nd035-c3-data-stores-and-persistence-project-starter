package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        logger.info("Saving new Schedule");
        return scheduleDTOFrom(
                scheduleService.save(scheduleOf(scheduleDTO)));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.findAll().stream()
                .map(schedule -> scheduleDTOFrom(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.findAllByPetsId(petId)
                .stream()
                .map(schedule -> scheduleDTOFrom(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.findAllByEmployeesId(employeeId)
                .stream()
                .map(schedule -> scheduleDTOFrom(schedule))
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.findAllByOwnerId(customerId)
                .stream()
                .map(schedule -> scheduleDTOFrom(schedule))
                .collect(Collectors.toList());
    }

    private Schedule scheduleOf(ScheduleDTO scheduleDTO) {
        Schedule schedule = Schedule.builder()
                .date(scheduleDTO.getDate())
                .activities(scheduleDTO.getActivities())
                .employees(scheduleDTO.getEmployeeIds().stream()
                        .map(employeeId -> employeeService.findById(employeeId))
                        .collect(Collectors.toList()))
                .pets(scheduleDTO.getPetIds().stream()
                        .map(petId -> petService.findById(petId))
                        .collect(Collectors.toList()))
                .build();

        return schedule;
    }

    private ScheduleDTO scheduleDTOFrom(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setActivities(schedule.getActivities());
        scheduleDTO.setPetIds(schedule.getPets()
                .stream().map(pet -> pet.getId()).collect(Collectors.toList()));
        scheduleDTO.setEmployeeIds(schedule.getEmployees()
                .stream().map(employee -> employee.getId()).collect(Collectors.toList()));
        scheduleDTO.setDate(schedule.getDate());

        return scheduleDTO;
    }
}
