package cn.lenchu.slms.controller;

import cn.lenchu.slms.entity.Admin;
import cn.lenchu.slms.entity.User;
import cn.lenchu.slms.service.AdminService;
import cn.lenchu.slms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AdminService adminService;
    private final UserService userService;

    @Autowired
    public AuthController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @PostMapping("/admin/{name}")
    public Admin adminLogin(@PathVariable String name, @RequestParam String pwd, HttpSession session, HttpServletResponse resp) {
        Admin a = adminService.login(name, pwd);
        if (a == null)
            resp.setStatus(404);
        else
            session.setAttribute("admin", a);
        return a;
    }

    @GetMapping("/admin/{name}")
    public void adminLogout(@PathVariable String name, @SessionAttribute(name = "admin") Admin admin,
                            HttpSession session, HttpServletResponse resp) {
        if (name != null && name.equals(admin.getName()))
            session.removeAttribute("admin");
        else
            resp.setStatus(403);
    }

    @GetMapping("/user/{email}")
    public void isEmailExists(@PathVariable String email, HttpSession session, HttpServletResponse resp,
                                @SessionAttribute(name = "email", required = false) String semail) {
        if (semail != null && semail.equals(email))
            session.setAttribute("email_code", userService.sendEmailCode(semail));
        else {
            if (userService.isEmailRegistered(email))
                resp.setStatus(403);
            else
                session.setAttribute("email", email);
        }
    }

    @PutMapping("/user")
    public void updateUser(@SessionAttribute String email, @SessionAttribute String email_code,
                           @RequestParam String code, @RequestParam String pwd) {
        if (code != null && code.equals(email_code)) {
            User u = userService.findUserByEmail(email);
            u.setPwd(userService.encryptPwd(pwd));
            userService.updateUser(u);
        }
    }

    @PostMapping("/user")
    public User register(@SessionAttribute(name = "email") String email, User user, @RequestParam String code,
                         @SessionAttribute(name = "email_code") String emailCode, HttpSession session,
                         HttpServletResponse resp) {
        if (code == null || "".equals(code))
            resp.setStatus(403);
        else if (code.equals(emailCode)) {
            user.setEmail(email);
            user = userService.register(user);
            session.setAttribute("user", user);
            return user;
        } else
            resp.setStatus(403);
        return null;
    }

    @GetMapping("/user/imgcode")
    public void sendImgCode(HttpServletResponse resp, HttpSession session) throws Exception {
        char[] codes = userService.genRandCode(4,false);
        BufferedImage img = userService.genImgCode(codes,"png");
        resp.setContentType("image/png");
        session.setAttribute("img_code", new String(codes));
        ImageIO.write(img, "png", resp.getOutputStream());
    }

    @PostMapping("/user/{email}")
    public User userLogin(@PathVariable String email, @RequestParam(required = false) String pwd,
                          @SessionAttribute(name = "user", required = false) User user,
                          @SessionAttribute(name = "img_code", required = false) String imgCode,
                          @RequestParam(required = false) String code,
                          HttpSession session, HttpServletResponse resp) {
        if (user != null && email.equals(user.getEmail()))
            return user;
        if (code != null && code.equals(imgCode))
            user = userService.login(email, pwd);
        if (user != null)
            session.setAttribute("user", user);
        else
            resp.setStatus(404);
        return user;
    }

    @DeleteMapping("/user/{email}")
    public void userLogout(@PathVariable String email, @SessionAttribute(name = "user") User user,
                           HttpSession session, HttpServletResponse resp) {
        if (user != null && email.equals(user.getEmail()))
            session.removeAttribute("user");
        else
            resp.setStatus(403);
    }
}
