package com.bonusGo.Bonus.Go.controller;

import com.bonusGo.Bonus.Go.model.Objetivo;
import com.bonusGo.Bonus.Go.service.ObjetivoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/objetivos")
public class ObjetivoController {

    @Autowired
    private ObjetivoServiceImp objetivoService;

    @GetMapping("/error")
    public String getError(){
        return "Error en la app";
    }

    @PostMapping("/registrar")
    public ResponseEntity<Objetivo> registrarObjetivo(@RequestBody Objetivo objetivo, @RequestParam String categoria) {
        try {
            Objetivo nuevoObjetivo = objetivoService.registObjetivo(objetivo, categoria);
            return new ResponseEntity<>(nuevoObjetivo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Objetivo> actualizarMonedas(@PathVariable int id, @RequestParam int monedas) {
        try {
            Objetivo objetivoActualizado = objetivoService.actualizarMonedas(id, monedas);
            return new ResponseEntity<>(objetivoActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarObjetivo(@PathVariable int id) {
        try {
            objetivoService.eliminarObjetivo(id);
            return new ResponseEntity<>("Objetivo eliminado correctamente", HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Objetivo no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Objetivo>> listarObjetivos() {
        List<Objetivo> objetivos = objetivoService.listarObjetivos();
        return new ResponseEntity<>(objetivos, HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Objetivo> buscarObjetivo(@PathVariable int id) {
        try {
            Objetivo objetivo = objetivoService.buscarObjetivo(id);
            return new ResponseEntity<>(objetivo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
