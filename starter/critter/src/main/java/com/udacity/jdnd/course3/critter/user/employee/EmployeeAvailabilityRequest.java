package com.udacity.jdnd.course3.critter.user.employee;

import java.time.LocalDate;
import java.util.Set;

public class EmployeeAvailabilityRequest {

    private Set<EmployeeSkill> skills;
    private LocalDate date;

    public EmployeeAvailabilityRequest(Set<EmployeeSkill> skills, LocalDate date) {
        this.date = date;
        this.skills = skills;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public LocalDate getDate() {
        return date;
    }

}
