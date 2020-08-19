package com.project3.mycontact.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@Entity
@Data
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotEmpty
    private int age;

    private String hoddy;

    private String bloodType;

    private LocalDate birthday;

    private String job;

    @ToString.Exclude
    private String phoneNumber;


}
