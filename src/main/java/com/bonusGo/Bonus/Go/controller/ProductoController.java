package com.bonusGo.Bonus.Go.controller;

import com.bonusGo.Bonus.Go.model.Producto;
import com.bonusGo.Bonus.Go.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("producto")
public class ProductoController {

    //http://localhost:8080/producto/

    @Autowired
    private ProductoService productoService;

    @GetMapping("/error")
    public String getError(){
        return "Error en la app";
    }

    @PostMapping("/registrar")
    public ResponseEntity<Producto> registrarProducto(@RequestBody Producto producto, @RequestParam String tipo) {
        try {
            Producto nuevoProducto = productoService.registrarProducto(producto, tipo);
            return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizarMonedas(@PathVariable int id, @RequestParam int coste) {
        try {
            Producto productoActualizado = productoService.actualizarMonedas(id, coste);
            return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable int id) {
        try {
            productoService.eliminarProducto(id);
            return new ResponseEntity<>("Producto eliminado correctamente", HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Producto>> listaProductos() {
        List<Producto> productos = productoService.listaProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Producto> buscarProductoId(@PathVariable int id) {
        try {
            Producto producto = productoService.buscarProductoId(id);
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
