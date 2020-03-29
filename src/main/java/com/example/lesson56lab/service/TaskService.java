package com.example.lesson56lab.service;

import com.example.lesson56lab.dto.TaskDTO;
import com.example.lesson56lab.model.Task;
import com.example.lesson56lab.repository.TaskRepository;
import com.example.lesson56lab.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;


    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository=taskRepository;
        this.userRepository=userRepository;

    }

    public TaskDTO getUserTask(String idUser, String idTask) {
        var user = userRepository.getById(idUser);
        var task = taskRepository.findById(idTask);
        if (task.get().getUser().getId().equals(user.getId()))
            return TaskDTO.from(task.get());
        else
            return null;
    }

    public Slice<TaskDTO> getUserTasks(String idUser, Pageable pageable) {
        var user = userRepository.getById(idUser);
        Iterable<Task> tasks = taskRepository.findAllByUser(user);
        List<Task> taskList = new ArrayList<>();
        tasks.forEach(taskList::add);
        Page<Task> page = new PageImpl<>(taskList, pageable, taskList.size());
        return page.map(TaskDTO::from);
    }

    public TaskDTO createTask(TaskDTO taskDTO, String idUser) {
        var task = Task.builder()
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .date(taskDTO.getDate())
                .user(userRepository.getById(idUser))
                .status("new task")
                .build();
        taskRepository.save(task);
        return TaskDTO.from(task);
    }

    public TaskDTO changeStatus(String idTask) {
        Optional<Task> oldTask = taskRepository.findById(idTask);
        var task = Task.builder()
                .id(oldTask.get().getId())
                .title(oldTask.get().getTitle())
                .description(oldTask.get().getDescription())
                .date(oldTask.get().getDate())
                .user(oldTask.get().getUser())
                .status(getNewStatus(oldTask.get().getStatus()))
                .build();
        taskRepository.save(task);
        return TaskDTO.from(task);
    }

    public String getNewStatus(String oldStatus) {
        if (oldStatus.equals("new task"))
            return "in the process";
        else
            return "closed";
    }
}
