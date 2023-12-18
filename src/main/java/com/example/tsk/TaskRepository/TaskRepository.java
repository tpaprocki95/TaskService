package com.example.tsk.TaskRepository;

import com.example.tsk.Enums.TaskEnum;
import com.example.tsk.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskEnum status);
}
