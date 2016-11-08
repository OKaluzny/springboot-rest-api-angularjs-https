package com.kaluzny.domain;

import javax.persistence.*;

@Entity
@Table(name = "user_title")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", nullable = false, updatable = false)
    private long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "VALUE", nullable = false)
    private long value;

    User() {
    }

    public User(long id) {
        this.id = id;
    }

    public User(long id, String title, long value) {
        this.id = id;
        this.title = title;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", value=" + value +
                '}';
    }
}