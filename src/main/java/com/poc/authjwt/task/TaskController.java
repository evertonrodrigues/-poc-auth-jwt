package com.poc.authjwt.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping
    public void save(@RequestBody Task task){
        Assert.notNull(task, "Task cannot be null.");
        Assert.notNull(task.getDescription(), "Task description cannot be null.");
        taskRepository.save(task);
    }

    @GetMapping
    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    @PutMapping("/{id}")
    public void editTask(@PathVariable long id, @RequestBody Task task) {
        Optional<Task> existingTask = taskRepository.findById(id);
        Assert.notNull(existingTask, "Task not found");
        existingTask.get().setDescription(task.getDescription());
        taskRepository.save(existingTask.get());
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id) {
        taskRepository.deleteById(id);
    }

}
