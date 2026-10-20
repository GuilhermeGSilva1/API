package api_teste.ds.controllers;

import java.net.URI;
import java.util.List;

import api_teste.ds.models.Task;
import api_teste.ds.services.TaskService;
import api_teste.ds.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/task")

@Validated

@CrossOrigin("*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {

        Task obj = this.taskService.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> findAllByUserId(
            @PathVariable("userId") Long userId) {

        this.userService.findById(userId);

        List<Task> objs = this.taskService.findAllByUserId(userId);

        return ResponseEntity.ok().body(objs);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody Task obj) {

        this.taskService.create(obj);

        URI url = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();

        return ResponseEntity.created(url).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @Valid @RequestBody Task obj,
            @PathVariable Long id) {

        obj.setId(id);

        this.taskService.update(obj);

        return ResponseEntity.noContent().build();
    }
}