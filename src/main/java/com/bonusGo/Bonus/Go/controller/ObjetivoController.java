package com.bonusGo.Bonus.Go.controller;

import com.bonusGo.Bonus.Go.model.Objetivo;
import com.bonusGo.Bonus.Go.service.ObjetivoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/objetivos")
public class ObjetivoController {

    @Autowired
    private ObjetivoServiceImp objetivoService;

    @GetMapping("/error")
    public String getError(){
        return "Error en la app";
    }

    @PostMapping("/registrar")
    public ResponseEntity<Objetivo> registrarObjetivo(@RequestBody Objetivo objetivo) {
        try {
            Objetivo nuevoObjetivo = objetivoService.registObjetivo(objetivo);
            return new ResponseEntity<>(nuevoObjetivo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Objetivo> actualizarObjetivo(@PathVariable int id, @RequestBody Objetivo objetivo) {
        try {
            Objetivo actualizado = objetivoService.actualizar(id, objetivo);
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //Tema isEnables
    @GetMapping("/habilitados")
    public ResponseEntity<List<Objetivo>> listarHabilitados() {
        return new ResponseEntity<>(objetivoService.listarHabilitados(), HttpStatus.OK);
    }

    @GetMapping("/deshabilitados")
    public ResponseEntity<List<Objetivo>> listarDeshabilitados() {
        return new ResponseEntity<>(objetivoService.listarDeshabilitados(), HttpStatus.OK);
    }

    @PutMapping("/deshabilitar/{id}")
    public ResponseEntity<String> deshabilitar(@PathVariable int id) {
        objetivoService.setEstado(id, false);
        return ResponseEntity.ok("Objetivo deshabilitado");
    }

    @PutMapping("/habilitar/{id}")
    public ResponseEntity<String> habilitar(@PathVariable int id) {
        objetivoService.setEstado(id, true);
        return ResponseEntity.ok("Objetivo habilitado");
    }

    //tema del canje del objetivo
    @PutMapping("/canjear/{idObjetivo}")
    public ResponseEntity<String> canjearObjetivo(@PathVariable int idObjetivo, @RequestParam int idUsuario) {
        try {
            objetivoService.canjearObjetivo(idObjetivo, idUsuario);
            return ResponseEntity.ok("Objetivo canjeado con éxito. ¡Has ganado monedas!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
