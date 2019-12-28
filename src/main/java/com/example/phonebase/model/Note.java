package com.example.phonebase.model;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import javax.persistence.*;


@Entity
@Table(name = "NOTES")
public class Note extends AbstractNamedEntity {

    @Column(name = "number",nullable = false)
    private String number;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NonNull
    private User user;

    public Note() {
    }
    public Note(String name, String number, String comment){
        this(null,name,number,comment);
    }

    public Note(Integer id, String name, String number, String comment) {
        super(id, name);
        this.number = number;
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
