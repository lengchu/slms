package cn.lenchu.slms.service.impl;

import cn.lenchu.slms.entity.User;
import cn.lenchu.slms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UserServiceImpl implements cn.lenchu.slms.service.UserService {

    @Autowired
    private UserRepository userDao;

    @Override
    public User findUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User saveOrUpdateUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User login(String email, String pwd) {
        return userDao.findByEmailAndPwd(email, pwd);
    }

    @Override
    public User findUserById(Integer id) {
        return userDao.findById(id).get();
    }

    @Override
    public Page<User> findAllUser(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return userDao.findAll(pageable);
    }

    @Override
    public Page<User> findAllUserOrderByCtime(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size, new Sort("ctime"));
        return userDao.findAll(pageable);
    }

    @Override
    public void deleteUserById(int id) {
        userDao.deleteById(id);
    }

    @Override
    public void deleteUser(User u) {
        userDao.delete(u);
    }
}
