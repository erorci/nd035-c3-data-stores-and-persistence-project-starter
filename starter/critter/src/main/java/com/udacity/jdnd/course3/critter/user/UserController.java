package com.udacity.jdnd.course3.critter.user;

import com.google.common.collect.Lists;
import com.udacity.jdnd.course3.critter.user.employee.*;
import org.assertj.core.util.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    EmployeeService employeeService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        logger.info("Saving new Employee");

        Employee employee = employeeOf(employeeDTO);
        Employee employeeSaved = employeeService.save(employee);
        employeeDTO.setId(employeeSaved.getId());
        logger.info("Saved new Employee with Id {}", employeeDTO.getId());

        return employeeDTO;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        logger.info("Getting Employee with Id {}", employeeId);
        Employee employeeFound = employeeService.findById(employeeId);

        return employeeDTOFrom(employeeFound);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        EmployeeAvailabilityRequest request =
                new EmployeeAvailabilityRequest(employeeDTO.getSkills(), employeeDTO.getDate());

        return Collections.unmodifiableList(employeeService.findEmployeeAvailability(request)
                .stream()
                .map(employee -> employeeDTOFrom(employee))
                .collect(Collectors.toList()));
    }

    private Employee employeeOf(EmployeeDTO employeeDTO) {
        Employee employee = Employee.builder()
                .name(employeeDTO.getName())
                .skills(employeeDTO.getSkills() != null ?
                        Lists.newArrayList(employeeDTO.getSkills()) :
                        Lists.newArrayList())
                .daysAvailable(employeeDTO.getDaysAvailable() != null ?
                        Lists.newArrayList(employeeDTO.getDaysAvailable()) :
                        Lists.newArrayList())
                .build();

        return employee;
    }

    private EmployeeDTO employeeDTOFrom(Employee employeeFound) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employeeFound.getId());
        employeeDTO.setName(employeeFound.getName());
        employeeDTO.setSkills(Sets.newHashSet(employeeFound.getSkills()));
        employeeDTO.setDaysAvailable(Sets.newHashSet(employeeFound.getDaysAvailable()));

        return employeeDTO;
    }
}
