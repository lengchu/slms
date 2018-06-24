package cn.lenchu.slms.controller;

import cn.lenchu.slms.entity.Admin;
import cn.lenchu.slms.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/{name}")
    public Admin findByName(@PathVariable String name) {
        return adminService.findByName(name);
    }

    @GetMapping("/")
    public List<Admin> findAll(@RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "pagesize", required = false) Integer pageSize) {
        return adminService.findAllAdmin(PageRequest.of((page == null ? 0 : page),
                                (pageSize == null ? 15 : pageSize))).getContent();
    }

    @PostMapping("/")
    public void add(@RequestParam("name") String name, @RequestParam("pwd") String pwd) {
        Admin admin = new Admin(name, pwd, null);
        adminService.saveAdmin(admin);
    }

    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        adminService.deleteAdmin(name);
    }
}
