package cn.lenchu.slms.service.impl;

import cn.lenchu.slms.entity.User;
import cn.lenchu.slms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Random;

@Transactional
@Service
public class UserServiceImpl implements cn.lenchu.slms.service.UserService {

    private final UserRepository userDao;
    private final JavaMailSender mailSender;

    @Autowired
    public UserServiceImpl(UserRepository userDao, JavaMailSender mailSender) {
		this.userDao = userDao;
		this.mailSender = mailSender;
	}

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

    /**
     * 1.3 发送邮箱验证码
     * @param email 邮箱
     * @return 验证码
     */
    @Override
    public String sendEmailCode(String email) {
    	SimpleMailMessage msg = new SimpleMailMessage();
    	msg.setTo(email);
    	msg.setSubject("验证码");
    	String code = new String(this.genRandCode(6, true));
    	msg.setFrom(((JavaMailSenderImpl)mailSender).getUsername());
    	msg.setText("您的验证码是: " + code + ", 请尽快完成验证!");
    	mailSender.send(msg);
        return code;
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

    /**
     * 1.9 生成指定长度的随机长度字符
     * @param length 指定长度
     * @param numOnly 是否纯数字
     * @return 随机字符数组
     */
    @Override
    public char[] genRandCode(int length, boolean numOnly) {
        // 48-57 - 0-9  65-90 A-Z  97-122 a-z
        Random r = new Random();
        r.setSeed(System.currentTimeMillis() + Thread.currentThread().getId());
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            int rand = numOnly ? r.nextInt(10) : r.nextInt(62);
            if (rand < 10) {
                result[i] = (char)(rand + 48);
            } else if (rand > 35) {
                result[i] = (char)(rand + 61);
            } else {
                result[i] = (char)(rand + 55);
            }
        }
        return result;
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
