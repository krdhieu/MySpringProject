package com.app.http;

import com.app.entity.Permission;
import com.app.service.PermissionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "myproject", tags = {"permission"})
@RequestMapping("/api/v1/permission")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<Permission>> getAllPermission() {
        return new ResponseEntity<>(permissionService.getAllPermission(), HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public @ResponseBody ResponseEntity<Permission> findPermissionById(@PathVariable("id") Long permissionId) {
        return new ResponseEntity<>(permissionService.findPermissionById(permissionId), HttpStatus.OK);
    }

    @GetMapping("/find-by-name/{name}")
    public @ResponseBody ResponseEntity<List<Permission>> findPermissionByName(@PathVariable("name") String permissionName) {
        return new ResponseEntity<>(permissionService.findPermissionByName(permissionName), HttpStatus.OK);
    }

}
