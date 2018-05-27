package cn.lenchu.slms.repository;

import cn.lenchu.slms.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByEmail(String email);

    public User findByEmailAndPwd(String email, String pwd);

    public Page<User> findByCtimeBetween(Date start, Date end, Pageable pageable);
}
