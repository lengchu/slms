package cn.lenchu.slms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "b_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer uid;

    @Column
    private Integer bid;

    @Column(name = "create_time")
    private Date ctime;

    @Column(name = "is_back")
    private boolean isBack;

    public Order() {
    }

    public Order(Integer uid, Integer bid, Date ctime, boolean isBack) {
        this.uid = uid;
        this.bid = bid;
        this.ctime = ctime;
        this.isBack = isBack;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", uid=" + uid +
                ", bid=" + bid +
                ", ctime=" + ctime +
                ", isBack=" + isBack +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public boolean isBack() {
        return isBack;
    }

    public void setBack(boolean back) {
        isBack = back;
    }
}
