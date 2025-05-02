package com.bonusGo.Bonus.Go.controller;

import com.bonusGo.Bonus.Go.model.Producto;
import com.bonusGo.Bonus.Go.model.Transaccion;
import com.bonusGo.Bonus.Go.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @PostMapping("/canjear")
    public Transaccion canjearProducto(@RequestParam int idUsuario, @RequestParam int idProducto) {
        return transaccionService.canjearProducto(idUsuario, idProducto);
    }

    // historial
    @GetMapping("/canjeados/{idUsuario}")
    public ResponseEntity<List<Producto>> obtenerProductosCanjeados(@PathVariable int idUsuario) {
        List<Producto> productos = transaccionService.obtenerProductosCanjeadosPorUsuario(idUsuario);
        if (productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

}
