package com.project3.mycontact.domain;


import com.project3.mycontact.controller.dto.PersonDto;
import com.project3.mycontact.domain.dto.Birthday;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Where(clause = "deleted = false")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Min(1)
    private Integer age;

    private String hobby;

    @NotEmpty
    @Column(nullable = false)
    @NonNull
    private String bloodType;

    private String address;

    @Valid
    @Embedded
    private Birthday birthday;

    private String job;

    @ToString.Exclude
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Block block;

    @ColumnDefault("0")
    private boolean deleted;

    public void set(PersonDto personDto){
        if(personDto.getAge() != 0 ){
            this.setAge(personDto.getAge());
        }

        if(!StringUtils.isEmpty(personDto.getHobby())){
            this.setHobby(personDto.getHobby());
        }

        if(!StringUtils.isEmpty(personDto.getBloodType())){
            this.setBloodType(personDto.getBloodType());
        }

        if(!StringUtils.isEmpty(personDto.getAddress())){
            this.setAddress(personDto.getAddress());
        }

        if(!StringUtils.isEmpty(personDto.getJob())){
            this.setJob(personDto.getJob());
        }

        if(!StringUtils.isEmpty(personDto.getPhoneNumber())){
            this.setPhoneNumber(personDto.getPhoneNumber());
        }

    }
}
