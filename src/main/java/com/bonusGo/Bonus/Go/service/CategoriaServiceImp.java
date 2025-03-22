package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Categoria;
import com.bonusGo.Bonus.Go.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImp implements CategoriaService{
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> getCategorias(){
        return categoriaRepository.findAll();
    }
}
