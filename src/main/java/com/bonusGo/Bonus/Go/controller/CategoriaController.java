package com.bonusGo.Bonus.Go.controller;

import com.bonusGo.Bonus.Go.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categoria")
public class CategoriaController {

    //http://localhost:8080/categoria/

    @Autowired
    private CategoriaService categoriaService;
}
