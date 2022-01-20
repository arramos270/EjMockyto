package com.salesianostriana.dam.ejerciciotesting.services;

import com.salesianostriana.dam.ejerciciotesting.model.Cliente;
import com.salesianostriana.dam.ejerciciotesting.model.LineaDeVenta;
import com.salesianostriana.dam.ejerciciotesting.model.Venta;
import com.salesianostriana.dam.ejerciciotesting.repos.ProductoRepositorio;
import com.salesianostriana.dam.ejerciciotesting.repos.VentaRepositorio;
import javassist.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = VentaServicio.class)
@AutoConfigureMockMvc
@DisplayName("VentaServicio Testing Class")
class VentaServicioTest {

    @MockBean
    private VentaRepositorio ventaRepositorio;

    @MockBean
    private ProductoRepositorio productoRepositorio;

    @MockBean
    private Cliente c;

    @MockBean
    private Venta v;

    @MockBean
    private LineaDeVenta lv1;

    private VentaServicio servicioVenta = new VentaServicio(ventaRepositorio, productoRepositorio);

    /* Caja Negra: nuevaVenta()
        -Código ó cantidad = nulo //Se comprueba con la notación
        -Código de un producto que no existe
     */
    @Test
    @NullAndEmptySource
    void whenNuevaVentaProductIdIsCero() {
        assertThrows(NotFoundException.class, () -> {servicioVenta.nuevaVenta(Map.of("0", 1), c);
        });
    }

    /* Caja Negra: nuevaVenta() //Caja Blanca: Pasamos por todas las líneas
        -Cliente nulo // Hecho en whenNuevaVentaClientIsNull()
     */
    @Test
    void whenNuevaVentaClientIsNull() {
        assertThrows(NullPointerException.class, () -> {servicioVenta.nuevaVenta(Map.of("1", 1), null);
        });
    }

    /*Correccion:
        -Funciona entero
        -Cuando id del producto no existe
        -Cantidad negativa
     */

    //

    /* Caja Negra: addProductoToVenta
        -Venta con id Nulo
     */

    @Test
    void addProductoToVentaWithVentaIdNotExistent() {
        assertThrows(NotFoundException.class, () -> {
            servicioVenta.addProductoToVenta(01743L, "1", 1);
        });
    }

    /* Caja Negra: addProductoToVenta
        -Venta nula
     */

    @Test
    void addProductoToVentaWithVentaNull() {
        assertThrows(NullPointerException.class, () -> {
            servicioVenta.addProductoToVenta(null, "1", 1);
        });
    }

    /* Caja Negra: addProductoToVenta
        -Venta con codigo de producto que no existe
     */
    @Test
    void addProductoToVentaWithProductDontExistent() {
        ventaRepositorio.save(v);
        assertThrows(NotFoundException.class, () -> {
            servicioVenta.addProductoToVenta(v.getId(), "0", 1);
        });
    }

    /* Caja Negra: addProductoToVenta
        -Cantidad de producto a añadir negativa
     */
    @Test
    void addProductoToVentaWithIdNegativeQuantity() {
        ventaRepositorio.save(v);
        assertThrows(ArithmeticException.class, () -> {
            servicioVenta.addProductoToVenta(v.getId(), "1", -1);
        });
    }

    /* Caja Blanca: addProductoToVenta
       ->Entra en todas las líneas de código */

    //

    /* Caja Negra: removeLineaVenta
        -idVenta no existe
     */
    @Test
    void removeLineaVentaWithVentaIdNotExist() {
        assertThrows(NotFoundException.class, () -> {
            servicioVenta.removeLineaVenta(0L, "1");
        });
    }

    /* Caja Negra: removeLineaVenta
        -Producto no en la venta
     */
    @Test
    void removeLineaVentaWithVentaProductNotInList() {
        ventaRepositorio.save(v);
        assertThrows(NotFoundException.class, () -> {
            servicioVenta.removeLineaVenta(v.getId(), "1");
        });
    }

    /* Caja Negra: removeLineaVenta
        -Codigo de producto nulo
     */
    @Test
    void removeLineaVentaWithProductoCodeNull() {
        ventaRepositorio.save(v);
        assertThrows(NullPointerException.class, () -> {
            servicioVenta.removeLineaVenta(v.getId(), null);
        });
    }

    /* Caja Blanca: removeLineaVenta
     */
    @Test
    void removeLineaVenta() {
        ventaRepositorio.save(v);
        v.setLineasDeVenta(List.of(lv1));
        assertThrows(NullPointerException.class, () -> {
            servicioVenta.removeLineaVenta(v.getId(), "1");
        });
    }



}