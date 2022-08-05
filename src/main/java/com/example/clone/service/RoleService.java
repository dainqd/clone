package com.example.clone.service;

import com.example.clone.entity.Role;
import com.example.clone.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role save(Role srole){
        return roleRepository.save(srole);
    }
}