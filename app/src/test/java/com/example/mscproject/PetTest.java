package com.example.mscproject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

public class PetTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getLevel() {
    }


    // original test made
    @Test
    public void step(){

        Pet pet = new Pet(0, "Fido", PetType.Bronchiosaurus, 0,null);

        Date date = new Date();
        long timestamp = date.getTime();
        pet.step(timestamp);

        assertEquals(1, pet.getLevel());

    }

}