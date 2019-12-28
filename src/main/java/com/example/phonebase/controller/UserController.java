package com.example.phonebase.controller;

import com.example.phonebase.model.User;
import com.example.phonebase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.phonebase.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = UserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    static final String REST_URL = "/users";

    private UserService service;
    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAll(){
        return service.getAll();
    }

    @GetMapping("/get.by.id/{id}")
    public User getById(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping("/by")
    public List<User> getByName(@RequestParam String name){
        return service.findByName(name);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user, @PathVariable int id) {
        assureIdConsistent(user,id);
        service.update(user);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();

        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.service.create(user);
        return new ResponseEntity<>(user,headers,HttpStatus.CREATED);

       /* User created = service.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);*/

    }
}
