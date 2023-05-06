package com.app.logic;

import com.app.entity.Role;
import com.app.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleLogic implements EntityLogic<Role> {
    @Autowired
    RoleRepo roleRepo;

    @Override
    public void saveEntity(Role entity) {
        roleRepo.save(entity);
    }
}
