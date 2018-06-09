package cn.lenchu.slms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "b_admin")
public class Admin implements Serializable {
    @Id
    private String name;

    @Column
    private String pwd;

    @Column(name = "create_time")
    private Date ctime;

    public Admin() {
    }

    public Admin(String name, String pwd, Date ctime) {
        this.name = name;
        this.pwd = pwd;
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", ctime=" + ctime +
                '}';
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
