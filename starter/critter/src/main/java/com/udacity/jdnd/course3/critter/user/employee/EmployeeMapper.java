package com.udacity.jdnd.course3.critter.user.employee;

import com.google.common.collect.Lists;
import org.assertj.core.util.Sets;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee employeeOf(EmployeeDTO employeeDTO) {
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

    public EmployeeDTO employeeDTOFrom(Employee employeeFound) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employeeFound.getId());
        employeeDTO.setName(employeeFound.getName());
        employeeDTO.setSkills(Sets.newHashSet(employeeFound.getSkills()));
        employeeDTO.setDaysAvailable(employeeFound.getDaysAvailable().size() > 0 ?
                Sets.newHashSet(employeeFound.getDaysAvailable())
                : null);

        return employeeDTO;
    }
}
