package cn.lenchu.slms.controller;

import cn.lenchu.slms.entity.User;
import cn.lenchu.slms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/")
    public void updateName(@RequestParam String name, @SessionAttribute User user) {
        if (user != null && !"".equals(name)) {
            user.setName(name);
            userService.updateUser(user);
        }
    }

    @PostMapping("/")
    public void updatePwd(@RequestParam String oldp, @RequestParam String newp, @SessionAttribute User user) {
        if (oldp != null && userService.encryptPwd(oldp).equals(user.getPwd())) {
            user.setPwd(userService.encryptPwd(newp));
            userService.updateUser(user);
        }
    }
}
