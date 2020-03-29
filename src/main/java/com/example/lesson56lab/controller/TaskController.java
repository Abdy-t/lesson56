package com.example.lesson56lab.controller;

import com.example.lesson56lab.annotations.ApiPageable;
import com.example.lesson56lab.dto.TaskDTO;
import com.example.lesson56lab.service.TaskService;
import com.example.lesson56lab.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService=taskService;
        this.userService=userService;

    }

    @ApiPageable
    @GetMapping
    public Slice<TaskDTO> getUserTasks(@ApiIgnore Pageable pageable) {
        var user = userService.getUser();
        return taskService.getUserTasks(user.getId(), pageable);
    }

    @GetMapping("/{idTask}")
    public TaskDTO getUserTask(@PathVariable String idTask) {
        var user = userService.getUser();
        return taskService.getUserTask(user.getId(), idTask);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        var user = userService.getUser();
        return taskService.createTask(taskDTO, user.getId());
    }

    @PostMapping(path = "/{idTask}")
    public TaskDTO changeTask(@PathVariable String idTask) {
        return taskService.changeStatus(idTask);
    }
}
