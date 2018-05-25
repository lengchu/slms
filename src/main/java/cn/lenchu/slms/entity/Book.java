package cn.lenchu.slms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "b_book")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String author;

    @Column
    private String location;

    @Column
    private String imgUri;

    @Column
    private boolean isBorrowed;

    @ManyToMany
    private Set<Type> types;

    public Book() {
    }

    public Book(String name, String author, String location, String imgUri, boolean isBorrowed) {
        this.name = name;
        this.author = author;
        this.location = location;
        this.imgUri = imgUri;
        this.isBorrowed = isBorrowed;
    }

    public Set<Type> getTypes() {
        return types;
    }

    public void setTypes(Set<Type> types) {
        this.types = types;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }
}
