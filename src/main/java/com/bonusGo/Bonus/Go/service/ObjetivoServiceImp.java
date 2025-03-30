package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Categoria;
import com.bonusGo.Bonus.Go.model.Objetivo;
import com.bonusGo.Bonus.Go.repository.ObjetivoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjetivoServiceImp implements ObjetivoService {

    private ObjetivoRepository objetivoRepository;

    @Override
    public Objetivo registObjetivo(Objetivo objetivo, String categoria) {
        Categoria c = Categoria.valueOf(categoria);
        objetivo.setCategoria(c);
        return objetivoRepository.save(objetivo);
    }

    @Override
    public Objetivo actualizarMonedas(int id, int monedas) {
        Objetivo objetivo = objetivoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        objetivo.setMonedas(monedas);
        return objetivoRepository.save(objetivo);
    }

    @Override
    public void eliminarObjetivo(int id) {
        Objetivo objetivo = objetivoRepository.findById(id).get();

        if (objetivo != null) {
            objetivoRepository.delete(objetivo);
        }else {
            System.out.println("Este objetivo no existe");
        }
    }

    @Override
    public List<Objetivo> listarObjetivos() {
        return objetivoRepository.findAll();
    }

    @Override
    public Objetivo buscarObjetivo(int id) {
        return objetivoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
}
