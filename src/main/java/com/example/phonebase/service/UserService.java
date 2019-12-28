package com.example.phonebase.service;

import com.example.phonebase.dao.UserDAO;
import com.example.phonebase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import static com.example.phonebase.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    private UserDAO repository;
    @Autowired
    public void setRepository(UserDAO repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.findAll();
    }


    public User create(User user) {
        Assert.notNull(user,"user must be not null");
        return repository.save(user);
    }

    public void update(User user) {
        Assert.notNull(user,"user must be not null");
        repository.save(user);
    }

    public List<User> findByName(String name){
        List<User> resultList = new ArrayList<>();
        List<User> users = repository.findAll();
        for(User u : users){
            if (u.getName().toLowerCase().contains(name.toLowerCase())){
                resultList.add(u);
            }
        }
        return resultList;
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User getById(int id) {
        return repository.findById(id).orElse(null);
    }


    @Transactional
    public void enable(int id, boolean enabled) {
        User user = getById(id);
        user.setEnabled(enabled);
        repository.save(user);  // !! need only for JDBC implementation
    }





}
