package com.example.lesson56lab.repository;

import com.example.lesson56lab.model.Task;
import com.example.lesson56lab.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskRepository extends PagingAndSortingRepository<Task, String> {
    Iterable<Task> findAllByUser(User user);
}
