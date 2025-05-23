package org.allyrx.studentbudget.Controllers;

import org.allyrx.studentbudget.Services.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class roleController {

    private final RoleService roleService;

    public roleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "{id}")
    public  void setParent(@PathVariable Long id){
        roleService.setParentRole(id);
    }
}
