package com.app.repository;

import com.app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    @Query("Select r from Role r where r.name like %:name%")
    List<Role> findByName(@Param("name") String roleName);
}
