package com.app.service;

import com.app.entity.Role;
import com.app.logic.RoleLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService {
    @Autowired
    RoleLogic roleLogic;
    public List<Role> getAllRoles() {
        return roleLogic.getAllRoles();
    }

    public Role createRole(Role role) {
        return roleLogic.createRole(role);
    }

    public Role saveRole(Role role) {
        return roleLogic.saveRole(role);
    }

    public void deleteRole(Long id) {
        roleLogic.deleleRole(id);
    }

    public Role findRoleById(Long id) {
        return roleLogic.findRoleById(id);
    }

    public List<Role> findRoleByName(String name) {
        return roleLogic.findRoleByName(name);
    }
}
