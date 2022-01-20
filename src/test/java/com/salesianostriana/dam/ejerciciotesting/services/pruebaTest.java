package com.salesianostriana.dam.ejerciciotesting.services;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class pruebaTest {

    @Test
    public void testEquals(){
        String saludo = "hola";
        assertEquals("hola", saludo);
    }
}
