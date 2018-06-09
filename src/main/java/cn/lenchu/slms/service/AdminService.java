package cn.lenchu.slms.service;

import cn.lenchu.slms.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {
    /**
     * 4.1 管理员登录
     * @param name 管理员名称
     * @param pwd 管理员密码 未加密密码
     * @return Admin对象或空
     */
    Admin login(String name, String pwd);

    /**
     * 4.2 添加管理员
     * @param admin 管理员对象，无名称是修改密码，有名称是添加
     * @return
     */
    Admin saveAdmin(Admin admin);

    /**
     * 4.3 查询所有管理员
     * @param pageable 分页模型
     * @return 分页对象
     */
    Page<Admin> findAllAdmin(Pageable pageable);

    /**
     * 4.4 删除管理员
     * @param name 管理员名称
     */
    void deleteAdmin(String name);

    /**
     * 4.5 管理员是否存在
     * @param name 管理员名称
     * @return
     *      true 存在
     *      false 不存在
     */
    boolean isAdminExists(String name);
}
