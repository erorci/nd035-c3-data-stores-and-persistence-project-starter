package com.udacity.jdnd.course3.critter.user.employee;

import lombok.*;
import org.assertj.core.util.Lists;
import org.hibernate.annotations.Nationalized;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee{
    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    private String name;

    @ElementCollection
    @Builder.Default
    private List<EmployeeSkill> skills = new ArrayList<>();

    @ElementCollection
    @Builder.Default
    @Setter
    private List<DayOfWeek> daysAvailable = new ArrayList<>();
}