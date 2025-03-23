package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Rol;
import com.bonusGo.Bonus.Go.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImp implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Rol getRol(int id){
        return rolRepository.getRol(id);
    }
}
