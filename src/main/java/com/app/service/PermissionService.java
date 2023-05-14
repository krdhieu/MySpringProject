package com.app.service;

import com.app.entity.Permission;
import com.app.logic.PermissionLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    @Autowired
    PermissionLogic permissionLogic;

    public List<Permission> getAllPermission() {
        return permissionLogic.getAllPermission();
    }

    public Permission findPermissionById(Long id) {
        return permissionLogic.findPermissionById(id);
    }

    public List<Permission> findPermissionByName(String name) {
        return permissionLogic.findPermissionByName(name);
    }
}
