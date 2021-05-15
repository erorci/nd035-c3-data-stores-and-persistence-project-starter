package com.udacity.jdnd.course3.critter.user.employee;

import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String name;

    @ElementCollection
    @Builder.Default
    private List<EmployeeSkill> skills = new ArrayList<>();

    @ElementCollection
    @Builder.Default
    private List<DayOfWeek> daysAvailable = new ArrayList<>();
}