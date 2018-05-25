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
}
