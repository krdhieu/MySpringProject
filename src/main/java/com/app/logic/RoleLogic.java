package com.app.logic;

import com.app.entity.Role;
import com.app.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RoleLogic implements EntityLogic<Role, Long> {
    @Autowired
    RoleRepo roleRepo;

    public List<Role> getAllRoles() {
        System.out.println("logic controller get all");
        return roleRepo.findAll();
    }

    public Role createRole(Role role) {
        return roleRepo.save(role);
    }

    public void deleleRole(Long id) {
        roleRepo.deleteById(id);
    }

    public Role findRoleById(Long id) {
        Optional<Role> optionalRole = roleRepo.findById(id);
        if (optionalRole.isPresent()) {
            return optionalRole.get();
        }
        return null;
    }

    public List<Role> findRoleByName(String name) {
        List<Role> roles = roleRepo.findByName(name);
        if (roles.size() != 0) {
            return roles;
        }
        return null;
    }

    public Role saveRole(Role role) {
        Optional<Role> roleOptional = roleRepo.findById(role.getId());
        if (roleOptional.isPresent()) {
            Role existedRole = roleOptional.get();
            existedRole.withName(role.getName());
            return existedRole;
        }
        return null;
    }

    @Override
    public void saveEntity(Role entity) {
        roleRepo.save(entity);
    }

    @Override
    public Role findById(Long id) {
        Optional<Role> optionalRole = roleRepo.findById(id);
        if (optionalRole.isPresent()) {
            return optionalRole.get();
        }
        return null;
    }

}
