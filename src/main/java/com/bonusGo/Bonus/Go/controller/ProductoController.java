package com.bonusGo.Bonus.Go.controller;

import com.bonusGo.Bonus.Go.model.Producto;
import com.bonusGo.Bonus.Go.model.Tipo;
import com.bonusGo.Bonus.Go.service.ImagenService;
import com.bonusGo.Bonus.Go.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ImagenService imagenService;

    @GetMapping("/error")
    public String getError() {
        return "Error en la app";
    }

    @PostMapping("/registrar")
    public ResponseEntity<Producto> registrarProducto(@RequestBody Producto producto)
    {

        Producto nuevoProducto = productoService.registrarProducto(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
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

    @PutMapping("/actualizarProducto/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable int id,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("coste") int coste,
            @RequestParam("tipo") Tipo tipo,
            @RequestParam(value = "imagen", required = false) MultipartFile imagenFile
    ) {
        try {
            Producto productoExistente = productoService.buscarProductoId(id);

            productoExistente.setNombre(nombre);
            productoExistente.setDescripcion(descripcion);
            productoExistente.setCoste(coste);
            productoExistente.setTipo(tipo);

            if (imagenFile != null && !imagenFile.isEmpty()) {
                String urlImagen = imagenService.guardarImagen(imagenFile);
                productoExistente.setImagen(urlImagen);
            }

            Producto productoActualizado = productoService.registrarProducto(productoExistente);
            return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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

    @GetMapping("/habilitados")
    public ResponseEntity<List<Producto>> listarHabilitados() {
        return new ResponseEntity<>(productoService.listarHabilitados(), HttpStatus.OK);
    }

    @GetMapping("/deshabilitados")
    public ResponseEntity<List<Producto>> listarDeshabilitados() {
        return new ResponseEntity<>(productoService.listarDeshabilitados(), HttpStatus.OK);
    }

    @PutMapping("/deshabilitar/{id}")
    public ResponseEntity<String> deshabilitar(@PathVariable int id) {
        productoService.setEstado(id, false);
        return ResponseEntity.ok("Producto deshabilitado");
    }

    @PutMapping("/habilitar/{id}")
    public ResponseEntity<String> habilitar(@PathVariable int id) {
        productoService.setEstado(id, true);
        return ResponseEntity.ok("Producto habilitado");
    }

    @PutMapping("/canjear/{idProducto}")
    public ResponseEntity<String> canjearProducto(@PathVariable int idProducto, @RequestParam int idUsuario) {
        try {
            productoService.canjearProducto(idProducto, idUsuario);
            return ResponseEntity.ok("Producto canjeado con éxito.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/disponibles/{idUsuario}")
    public ResponseEntity<List<Producto>> productosDisponibles(@PathVariable int idUsuario) {
        List<Producto> disponibles = productoService.listarDisponiblesParaUsuario(idUsuario);
        return ResponseEntity.ok(disponibles);
    }
}

