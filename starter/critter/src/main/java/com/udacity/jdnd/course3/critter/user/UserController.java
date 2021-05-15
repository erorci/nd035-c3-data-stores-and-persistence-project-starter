package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.user.customer.Customer;
import com.udacity.jdnd.course3.critter.user.customer.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.customer.CustomerMapper;
import com.udacity.jdnd.course3.critter.user.customer.CustomerService;
import com.udacity.jdnd.course3.critter.user.employee.*;
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

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    CustomerMapper customerMapper;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        logger.info("Saving new Customer");
        Customer customerSaved = customerService.save(customerMapper.customerOf(customerDTO));
        logger.info("Saved new Customer with Id {}", customerSaved.getId());

        return customerMapper.customerDTOFrom(customerSaved);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        logger.info("Getting all Customer(s)");
        return customerService
                .findAll()
                .stream()
                .map(customer -> customerMapper.customerDTOFrom(customer))
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return customerMapper.customerDTOFrom(customerService.findOwnerByPetId(petId));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        logger.info("Saving new Employee");
        Employee employeeSaved = employeeService.save(employeeMapper.employeeOf(employeeDTO));
        logger.info("Saved new Employee with Id {}", employeeSaved.getId());

        return employeeMapper.employeeDTOFrom(employeeSaved);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        logger.info("Getting Employee with Id {}", employeeId);
        Employee employeeFound = employeeService.findById(employeeId);

        return employeeMapper.employeeDTOFrom(employeeFound);
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
                .map(employee -> employeeMapper.employeeDTOFrom(employee))
                .collect(Collectors.toList()));
    }
}
