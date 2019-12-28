package com.example.phonebase.to;
import com.example.phonebase.model.HasId;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public abstract class BaseTo implements HasId {
    protected Integer id;

    @NotBlank
    @Size(min = 2, max = 100)
    protected String name;

    public BaseTo() {
    }

    public BaseTo(Integer id,String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
