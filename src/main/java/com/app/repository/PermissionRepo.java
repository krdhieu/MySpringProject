package com.app.repository;

import com.app.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PermissionRepo extends JpaRepository<Permission, Long> {
    @Query("Select p from Permission p where p.name like %:name%")
    List<Permission> findPermissionByName(@Param("name") String permissionName);
}
