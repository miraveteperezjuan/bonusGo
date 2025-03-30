package com.bonusGo.Bonus.Go.controller;

import com.bonusGo.Bonus.Go.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rol")
public class RolController {

    //http://localhost:8080/rol/
    @Autowired
    private RolService rolService;

}
