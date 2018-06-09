package cn.lenchu.slms.service.impl;

import cn.lenchu.slms.entity.Admin;
import cn.lenchu.slms.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class AdminServiceImpl implements cn.lenchu.slms.service.AdminService {
    private final AdminRepository adminDao;

    @Autowired
    public AdminServiceImpl(AdminRepository adminDao) {
        this.adminDao = adminDao;
    }

    /**
     * 4.1 管理员登录
     * @param name 管理员名称
     * @param pwd 管理员密码 未加密密码
     * @return Admin对象或空
     */
    @Override
    public Admin login(String name, String pwd) {
        return this.adminDao.findByNameAndPwd(name, encryptPwd(pwd));
    }

    /**
     * 4.2 添加管理员
     * @param admin 管理员对象，无名称是修改密码，有名称是添加
     * @return
     */
    @Override
    public Admin saveAdmin(Admin admin) {
        admin.setPwd(encryptPwd(admin.getPwd()));
        if (admin.getCtime() == null)
            admin.setCtime(new Date());
        return this.adminDao.save(admin);
    }

    /**
     * 4.3 查询所有管理员
     * @param pageable 分页模型
     * @return 分页对象
     */
    @Override
    public Page<Admin> findAllAdmin(Pageable pageable) {
        return this.adminDao.findAll(pageable);
    }

    /**
     * 4.4 删除管理员
     * @param name 管理员名称
     */
    @Override
    public void deleteAdmin(String name) {
        this.adminDao.deleteById(name);
    }

    /**
     * 4.5 管理员是否存在
     * @param name 管理员名称
     * @return
     *      true 存在
     *      false 不存在
     */
    @Override
    public boolean isAdminExists(String name) {
        return this.adminDao.findById(name).get() != null;
    }

    /**
     * 加密密码
     * @param orgPwd 原始密码
     * @return 加密后的密码
     */
    private String encryptPwd(String orgPwd) {
        return DigestUtils.md5DigestAsHex(orgPwd.getBytes());
    }
}
