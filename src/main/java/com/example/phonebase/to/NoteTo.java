package com.example.phonebase.to;

import java.beans.ConstructorProperties;
import java.util.Objects;

public class NoteTo extends BaseTo {
    protected String number;
    protected String comment;

    public NoteTo(){
    }
    @ConstructorProperties({"id", "name","number","comment"})
    public NoteTo(Integer id, String name, String number, String comment) {
        super(id, name);
        this.number = number;
        this.comment = comment;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        NoteTo noteTo = (NoteTo) o;
        return number.equals(noteTo.getNumber()) &&
                comment.equals(noteTo.getComment())&&
                Objects.equals(id, noteTo.id) &&
                Objects.equals(name,noteTo.name)&&
                Objects.equals(comment,noteTo.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number,comment);
    }
}
