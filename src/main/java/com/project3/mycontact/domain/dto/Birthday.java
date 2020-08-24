package com.project3.mycontact.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Embeddable
@NoArgsConstructor
@Data
public class Birthday {
    private static final int KOREA_AGE;

    private Integer yearOfBirthday;
    private Integer monthOfBirthday;
    private Integer dayOfBirthday;


    static {
        KOREA_AGE =1;
    }

    private Birthday(LocalDate birthday){
        this.yearOfBirthday = birthday.getYear();
        this.monthOfBirthday = birthday.getMonthValue();
        this.dayOfBirthday = birthday.getDayOfMonth();
    }

    public int getAge(){
        return LocalDate.now().getYear() - this.yearOfBirthday + KOREA_AGE;
    }

    public boolean isBirthdayToday(){
        return LocalDate.now().equals(LocalDate.of(yearOfBirthday, monthOfBirthday, dayOfBirthday));
    }

    public static Birthday of(LocalDate birthday){
        return new Birthday(birthday);
    }
}
