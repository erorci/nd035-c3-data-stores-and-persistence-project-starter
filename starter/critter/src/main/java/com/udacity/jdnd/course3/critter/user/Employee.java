package com.udacity.jdnd.course3.critter.user;

import org.assertj.core.util.Lists;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.time.DayOfWeek;
import java.util.List;

@Entity
public class Employee extends User {
    @ElementCollection
    private List<EmployeeSkill> skills;

    @ElementCollection
    private List<DayOfWeek> daysAvailable;

    public Employee() {
        skills = Lists.emptyList();
        daysAvailable = Lists.emptyList();
    }

    public void addSkills(EmployeeSkill skill) {
        skills.add(skill);
    }

    public void addWorkDays(DayOfWeek day) {
        daysAvailable.add(day);
    }

    public List<EmployeeSkill> getSkills() {
        return Lists.newArrayList(skills);
    }

    public List<DayOfWeek> getDaysAvailable() {
        return Lists.newArrayList(daysAvailable);
    }
}