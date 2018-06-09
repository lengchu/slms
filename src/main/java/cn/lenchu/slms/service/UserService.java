package cn.lenchu.slms.service;

import cn.lenchu.slms.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.awt.image.BufferedImage;
import java.util.Date;

public interface UserService {
    /**
     * 1.1 邮箱是否已注册
     * @param email 邮箱
     * @return true: 已被注册  false: 未被注册
     */
    boolean isEmailRegistered(String email);

    /**
     * 1.2 用户注册
     * 密码加密放在服务层，注册时间也在服务层默认未当前时间
     * @param user User对象，需包含邮箱和未加密的密码，用户名可选
     * @return 注册后的User对象
     */
    User register(User user);

    /**
     * 1.3 发送邮箱验证码
     * @param email 邮箱
     * @return 验证码
     */
    String sendEmailCode(String email);

    /**
     * 1.4 加密密码
     * @param orgPwd 未加密的密码
     * @return 已加密的密码
     */
    String encryptPwd(String orgPwd);

    /**
     * 1.5 登录
     * @param email 邮箱
     * @param orgPwd 原始密码
     * @return User对象,空对象则登录失败
     */
    User login(String email, String orgPwd);

    /**
     * 1.6 修改用户信息
     * @param user 包含id和其他需修改的信息的用户对象
     * @return 修改后的对象
     */
    User updateUser(User user);

    /**
     * 1.7 查询所有用户
     * @param pageable 分页信息
     * @return 分页对象
     */
    Page<User> findAllUser(Pageable pageable);

    /**
     * 1.8 生成图片验证码
     * @param codes 验证码
     * @param imgType 图片格式
     * @return 图片验证码对象
     */
    BufferedImage genImgCode(char[] codes, String imgType) throws Exception;

    /**
     * 1.9 生成指定长度的随机长度字符
     * @param length 指定长度
     * @param numOnly 是否纯数字
     * @return 随机字符数组
     */
    char[] genRandCode(int length, boolean numOnly);

    /**
     * 1.10 查询指定时间段注册的用户
     * @param start 开始时间
     * @param end 结束时间
     * @param pageable 分页信息
     * @return 分页对象
     */
    Page<User> findAllUserByCtime(Date start, Date end, Pageable pageable);

    /**
     * 1.11 通过id删除用户
     * @param id 用户id
     */
    void deleteUserById(int id);
}
