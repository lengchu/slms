package cn.lenchu.slms.service;

import cn.lenchu.slms.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {
    User findUserByEmail(String email);

    User saveOrUpdateUser(User user);

    User login(String email, String pwd);

    User findUserById(Integer id);

    Page<User> findAllUser(int page, int size);

    Page<User> findAllUserOrderByCtime(int page, int size);

    void deleteUserById(int id);

    void deleteUser(User u);
}
