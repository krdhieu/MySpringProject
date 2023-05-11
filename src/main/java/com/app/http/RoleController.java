package com.app.http;

import com.app.entity.Role;
import com.app.service.RoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "myproject", tags = {"role"})
@RequestMapping("/api/v1/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<List<Role>>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @PostMapping("/find-by-name/{name}")
    public @ResponseBody ResponseEntity<List<Role>> findRolesByName(@PathVariable("name") String name) {
        if(roleService.findRoleByName(name) != null)
            return new ResponseEntity<>(roleService.findRoleByName(name), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
