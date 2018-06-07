package cn.lenchu.slms.service;

import cn.lenchu.slms.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface OrderService {
    /**
     * 3.1新增订单
     * @param uid 用户id
     * @param bid 书籍id
     * @return 订单对象
     */
    Order saveOrder(Integer uid, Integer bid);

    /**
     * 3.2修改订单为已归还
     * @param order 通过findById查出来的订单对象
     * @return 修改后的订单对象
     */
    Order backBook(Order order);

    /**
     * 3.3删除订单
     * @param id
     */
    void deleteOrderById(Integer id);

    /**
     * 3.4通过用户id查询订单
     * @param uid 用户id
     * @param pageable 分页模型
     * @return 分页对象
     */
    Page<Order> findOrderByUserId(Integer uid, Pageable pageable);

    /**
     * 3.5通过图书id查询订单
     * @param bid 图书id
     * @param pageable 分页模型
     * @return 分页对象
     */
    Page<Order> findOrderByBookId(Integer bid, Pageable pageable);

    /**
     * 3.6查询指定时间段的订单
     * @param start 开始时间
     * @param end 结束时间
     * @param pageable 分页模型
     * @return 分页对象
     */
    Page<Order> findOrderByCtimeBetween(Date start, Date end, Pageable pageable);

    /**
     * 3.7查询所有订单
     * @param isBack 是否归还
     *               true: 已归还
     *               false: 未归还
     * @param pageable
     * @return
     */
    Page<Order> findAllOrder(boolean isBack, Pageable pageable);

    /**
     * 3.8通过id查订单
     * @param id 订单id
     * @return 订单对象
     */
    Order findOrderById(Integer id);
}
