package com.salesianostriana.dam.ejerciciotesting.services;

import com.salesianostriana.dam.ejerciciotesting.model.Cliente;
import com.salesianostriana.dam.ejerciciotesting.model.LineaDeVenta;
import com.salesianostriana.dam.ejerciciotesting.model.Producto;
import com.salesianostriana.dam.ejerciciotesting.model.Venta;
import com.salesianostriana.dam.ejerciciotesting.repos.ProductoRepositorio;
import com.salesianostriana.dam.ejerciciotesting.repos.VentaRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VentaServicioMockytoTest {

    @MockBean
    private VentaRepositorio ventaRepositorio;

    @MockBean
    private ProductoRepositorio productoRepositorio;

    @Test
    void nuevaVenta() {

        Producto p = Producto.ProductoBuilder()
                .id(1L)
                .nombre()
                .dni("aaaaa4")
                .build();

        Venta v = Venta.builder()
                .id(1L)
                .cliente(null)
                .build();

    }
}