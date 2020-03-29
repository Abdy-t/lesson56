package com.example.lesson56lab.dto;

import com.example.lesson56lab.model.Task;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class TaskDTO {

    public static TaskDTO from(Task task) {
        return builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .date(task.getDate())
                .user(task.getUser().getEmail())
                .status(task.getStatus())
                .build();
    }

    private String id;
    private String title;
    private String description;
    private LocalDate date;
    private String user;
    private String status;

}
