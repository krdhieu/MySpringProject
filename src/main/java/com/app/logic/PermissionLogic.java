package com.app.logic;

import com.app.entity.Permission;
import com.app.logic.common.EntityLogic;
import com.app.repository.PermissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PermissionLogic implements EntityLogic<Permission, Long> {
    @Autowired
    PermissionRepo permissionRepo;

    public List<Permission> getAllPermission() {
        return permissionRepo.findAll();
    }

    public Permission findPermissionById(Long id) {
        Optional<Permission> permissionOptional = permissionRepo.findById(id);
        if(permissionOptional.isPresent())
            return permissionOptional.get();
        return null;
    }

    public List<Permission> findPermissionByName(String name) {
        return permissionRepo.findPermissionByName(name);
    }

    @Override
    public void saveEntity(Permission entity) {
        permissionRepo.save(entity);
    }

    @Override
    public Permission findById(Long id) {
        Optional<Permission> permissionOptional = permissionRepo.findById(id);
        if(permissionOptional.isPresent())
            return permissionOptional.get();
        return null;
    }
}
