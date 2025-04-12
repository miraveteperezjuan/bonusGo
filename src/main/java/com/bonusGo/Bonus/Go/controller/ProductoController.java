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
    public ResponseEntity<Producto> registrarProducto(@RequestBody Producto producto) {
        try {
            Producto nuevoProducto = productoService.registrarProducto(producto);
            return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizarMonedas(@PathVariable int id, @RequestParam int coste) {
        try {
            Producto productoActualizado = productoService.actualizarMonedas(id, coste);
            return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //Tema del isEnabled
    // Obtener productos habilitados
    @GetMapping("/habilitados")
    public ResponseEntity<List<Producto>> listarHabilitados() {
        return new ResponseEntity<>(productoService.listarHabilitados(), HttpStatus.OK);
    }

    // Obtener productos deshabilitados
    @GetMapping("/deshabilitados")
    public ResponseEntity<List<Producto>> listarDeshabilitados() {
        return new ResponseEntity<>(productoService.listarDeshabilitados(), HttpStatus.OK);
    }

    // Deshabilitar producto
    @PutMapping("/deshabilitar/{id}")
    public ResponseEntity<String> deshabilitar(@PathVariable int id) {
        productoService.setEstado(id, false);
        return ResponseEntity.ok("Producto deshabilitado");
    }

    // Rehabilitar producto
    @PutMapping("/habilitar/{id}")
    public ResponseEntity<String> habilitar(@PathVariable int id) {
        productoService.setEstado(id, true);
        return ResponseEntity.ok("Producto habilitado");
    }

    //Canjear los productos para el user
    @PutMapping("/canjear/{idProducto}")
    public ResponseEntity<String> canjearProducto(@PathVariable int idProducto, @RequestParam int idUsuario) {
        try {
            productoService.canjearProducto(idProducto, idUsuario);
            return ResponseEntity.ok("Producto canjeado con éxito.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }





}
