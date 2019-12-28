package com.example.phonebase.controller;

import com.example.phonebase.model.Note;
import com.example.phonebase.model.User;
import com.example.phonebase.service.NoteService;
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
@RequestMapping(value = NoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class NoteController {

    static final String REST_URL = "/notes";

    private NoteService service;
    @Autowired
    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Note> getAll(){
        return service.getAll();
    }

    @GetMapping("/get.by.id/{id}")
    public Note getById(@PathVariable int id) {
        return service.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Note note, @PathVariable int id) {
        assureIdConsistent(note,id);
        service.update(note);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> create(@RequestBody Note note) {
        HttpHeaders headers = new HttpHeaders();

        if(note == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.service.create(note);
        return new ResponseEntity<>(note,headers,HttpStatus.CREATED);

       /* User created = service.create(note);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);*/

    }
}
