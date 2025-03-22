package com.bonusGo.Bonus.Go.controller;

import com.bonusGo.Bonus.Go.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("producto")
public class ProductoController {

    //http://localhost:8080/producto/

    @Autowired
    private ProductoService productoService;

}
