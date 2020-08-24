package com.project3.mycontact.domain.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BirthdayTest {



    @Test
    void isBirthdayToday() {

        Birthday birthday = new Birthday();

        LocalDate test = LocalDate.of(1995, 8, 24);

        System.out.println(test.equals(LocalDate.now()));


    }
}