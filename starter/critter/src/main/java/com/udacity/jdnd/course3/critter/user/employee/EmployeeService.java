package com.udacity.jdnd.course3.critter.user.employee;

import com.udacity.jdnd.course3.critter.exception.ResourceNotFound;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findById(Long employee)  {
        return employeeRepository.findById(employee).orElseGet(null);
    }

    public void setAvailability(Set<DayOfWeek> dayOfWeekSet, Long employeeId) {
        Employee employee = findById(employeeId);
        employee.setDaysAvailable(Lists.newArrayList(dayOfWeekSet));
        save(employee);
    }

    public List<Employee> findEmployeeAvailability(EmployeeAvailabilityRequest request) {
        List<Employee> employeesAvailable = new ArrayList<>();

        Iterable<Employee> employees = employeeRepository.findAll();
        employees.forEach( employee -> {
            if (employee.getDaysAvailable().contains(request.getDate().getDayOfWeek())
                && employee.getSkills().containsAll(request.getSkills())) {

                employeesAvailable.add(employee);
            }
        });

        return employeesAvailable;
    }

}
