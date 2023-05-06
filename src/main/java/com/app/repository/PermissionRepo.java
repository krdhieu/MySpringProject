package com.app.repository;

import com.app.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PermissionRepo extends JpaRepository<Permission, Long> {
}
