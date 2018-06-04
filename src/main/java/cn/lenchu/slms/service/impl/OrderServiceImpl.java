package cn.lenchu.slms.service.impl;

import cn.lenchu.slms.entity.Order;
import cn.lenchu.slms.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class OrderServiceImpl {
    private final OrderRepository orderDao;

    @Autowired
    public OrderServiceImpl(OrderRepository orderDao) {
        this.orderDao = orderDao;
    }

    /**
     * 3.1新增订单
     * @param uid 用户id
     * @param bid 书籍id
     * @return 订单对象
     */
    public Order saveOrder(Integer uid, Integer bid) {
        Order order = new Order();
        order.setUid(uid);
        order.setBid(bid);
        order.setBack(false);
        order.setCtime(new Date());

        return this.orderDao.save(order);
    }

    /**
     * 3.2修改订单为已归还
     * @param order 通过findById查出来的订单对象
     * @return 修改后的订单对象
     */
    public Order backBook(Order order) {
        order.setBack(true);
        return this.orderDao.save(order);
    }

    /**
     * 3.3删除订单
     * @param id
     */
    public void deleteOrderById(Integer id) {
        this.orderDao.deleteById(id);
    }

    /**
     * 3.4通过用户id查询订单
     * @param uid 用户id
     * @param pageable 分页模型
     * @return 分页对象
     */
    public Page<Order> findOrderByUserId(Integer uid, Pageable pageable) {
        Order o = new Order();
        o.setUid(uid);
        return this.orderDao.findAll(Example.of(o), pageable);
    }

    /**
     * 3.5通过图书id查询订单
     * @param bid 图书id
     * @param pageable 分页模型
     * @return 分页对象
     */
    public Page<Order> findOrderByBookId(Integer bid, Pageable pageable) {
        Order o = new Order();
        o.setBid(bid);
        return this.orderDao.findAll(Example.of(o), pageable);
    }

    /**
     * 3.6查询指定时间段的订单
     * @param start 开始时间
     * @param end 结束时间
     * @param pageable 分页模型
     * @return 分页对象
     */
    public Page<Order> findOrderByCtimeBetween(Date start, Date end, Pageable pageable) {
        return this.orderDao.findByCtimeBetween(start, end, pageable);
    }

    /**
     * 3.7查询所有订单
     * @param isBack 是否归还
     *               true: 已归还
     *               false: 未归还
     * @param pageable
     * @return
     */
    public Page<Order> findAllOrder(boolean isBack, Pageable pageable) {
        Order o = new Order();
        o.setBack(isBack);
        return this.orderDao.findAll(Example.of(o), pageable);
    }

    /**
     * 3.8通过id查订单
     * @param id 订单id
     * @return 订单对象
     */
    public Order findOrderById(Integer id) {
        return this.orderDao.findById(id).get();
    }
}
