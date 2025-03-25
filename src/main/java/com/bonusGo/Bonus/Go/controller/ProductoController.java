package com.bonusGo.Bonus.Go.controller;

import com.bonusGo.Bonus.Go.model.Producto;
import com.bonusGo.Bonus.Go.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("producto")
public class ProductoController {

    //http://localhost:8080/producto/

    @Autowired
    private ProductoService productoService;

    @PostMapping("/saveProduct")
    public ResponseEntity<?> saveProducto(@RequestBody Producto producto) {
        return new ResponseEntity<>(productoService.save(producto), HttpStatus.CREATED);
    }

    @GetMapping("/listaProductos")
    public ResponseEntity<?> listaProductos(){
        return new ResponseEntity<>(productoService.listaProductos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable int id){
        return new ResponseEntity<>(productoService.buscaProducto(id), HttpStatus.OK);
    }

    @GetMapping("/borrarProducto/{id}")
    public ResponseEntity<?> borrarProductoById(@PathVariable int id){
        return new ResponseEntity<>(productoService.eliminaProducto(id), HttpStatus.OK);
    }

    @PostMapping("/editProduct")
    public ResponseEntity<?> editarProductoById(@RequestBody Producto producto){
        return new ResponseEntity<>(productoService.save(producto), HttpStatus.CREATED);
    }



}
