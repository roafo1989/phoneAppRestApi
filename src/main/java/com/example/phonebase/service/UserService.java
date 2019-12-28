package com.example.phonebase.service;

import com.example.phonebase.dao.UserDAO;
import com.example.phonebase.model.User;
import com.example.phonebase.to.UserTo;
import com.example.phonebase.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.List;

import static com.example.phonebase.util.ValidationUtil.checkNotFound;
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

    @Transactional
    public void update(UserTo userTo) {
        User user = getById(userTo.id());
        repository.save(UserUtil.updateFromTo(user, userTo));
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
