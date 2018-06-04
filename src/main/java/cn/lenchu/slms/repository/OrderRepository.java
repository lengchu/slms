package cn.lenchu.slms.repository;

import cn.lenchu.slms.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    public Page<Order> findByCtimeBetween(Date start, Date end, Pageable pageable);
}
