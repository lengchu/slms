package cn.lenchu.slms.service.impl;

import cn.lenchu.slms.entity.User;
import cn.lenchu.slms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.util.Date;

@Transactional
@Service
public class UserServiceImpl implements cn.lenchu.slms.service.UserService {

    @Autowired
    private UserRepository userDao;

    /**
     * 1.1 邮箱是否已注册
     * @param email 邮箱
     * @return true: 已被注册  false: 未被注册
     */
    @Override
    public boolean isEmailRegistered(String email) {
        return userDao.findByEmail(email) != null;
    }

    /**
     * 1.2 用户注册
     * 密码加密放在服务层，注册时间也在服务层默认未当前时间
     * @param user User对象，需包含邮箱和未加密的密码，用户名可选
     * @return 注册后的User对象
     */
    @Override
    public User register(User user) {
        user.setPwd(encryptPwd(user.getPwd()));
        user.setCtime(new Date());
        return userDao.save(user);
    }

    /**TODO
     * 1.3 发送邮箱验证码
     * @param email 邮箱
     * @return 验证码
     */
    @Override
    public String sendEmailCode(String email) {
        return null;
    }

    /**TODO
     * 1.4 加密密码
     * @param orgPwd 未加密的密码
     * @return 已加密的密码
     */
    @Override
    public String encryptPwd(String orgPwd) {
        return orgPwd;
    }

    /**
     * 1.5 登录
     * @param email 邮箱
     * @param pwd 原始密码
     * @return User对象,空对象则登录失败
     */
    @Override
    public User login(String email, String orgPwd) {
        return userDao.findByEmailAndPwd(email, encryptPwd(orgPwd));
    }

    /**
     * 1.6 修改用户信息
     * @param user 包含id和其他需修改的信息的用户对象
     * @return 修改后的对象
     */
    @Override
    public User updateUser(User user) {
        return userDao.save(user);
    }

    /**
     * 1.7 查询所有用户
     * @param pageable 分页信息
     * @return 分页对象
     */
    @Override
    public Page<User> findAllUser(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    /**TODO
     * 1.8 生成图片验证码
     * @param code 验证码
     * @return 图片验证码对象
     */
    @Override
    public BufferedImage genImgCode(String code) {
        return null;
    }

    /**TODO
     * 1.9 生成指定长度的随机字符串
     * @param length 指定长度
     * @return 随机字符串
     */
    @Override
    public String genRandCode(int length) {
        return null;
    }

    /**
     * 1.10 查询指定时间段注册的用户
     * @param start 开始时间
     * @param end 结束时间
     * @param pageable 分页信息
     * @return 分页对象
     */
    @Override
    public Page<User> findAllUserByCtime(Date start, Date end, Pageable pageable) {
        return userDao.findByCtimeBetween(start, end, pageable);
    }

    /**
     * 1.11 通过id删除用户
     * @param id 用户id
     */
    @Override
    public void deleteUserById(int id) {
        userDao.deleteById(id);
    }
}
