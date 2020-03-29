package com.example.lesson56lab.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
@Document(collection = "tasks")
@Data
public class Task {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String title;
    private String description;
    private LocalDate date;
    @DBRef
    private User user;
    private String status;
}
