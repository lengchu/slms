package cn.lenchu.slms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "b_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String pwd;

    @Column(name = "create_time")
    private Date ctime;

    public User() {
    }

    public User(String email, String name, String pwd, Date ctime) {
        this.email = email;
        this.name = name;
        this.pwd = pwd;
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", ctime=" + ctime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}
