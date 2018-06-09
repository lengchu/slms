package cn.lenchu.slms.repository;

import cn.lenchu.slms.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {

    public Admin findByNameAndPwd(String name, String pwd);
}
