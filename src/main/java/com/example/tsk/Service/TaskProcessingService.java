package com.example.tsk.Service;

import com.example.tsk.Enums.TaskEnum;
import com.example.tsk.Model.Task;
import com.example.tsk.Model.TaskResult;
import com.example.tsk.TaskRepository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class TaskProcessingService {

    @Autowired
    private TaskRepository taskRepository;

    @Async("taskExecutor")
    @Transactional
    public void processTaskAsync(Long taskId) throws InterruptedException {
        log.info("Processing task with id: {}", taskId);
        Task task = taskRepository.findById(taskId).orElseThrow();
        task.setStatus(TaskEnum.IN_PROGRESS);
        TaskResult result = PatternMatchingAlgorithm.findBestMatch(task.getPattern(), task.getInput());
        log.debug("For task with ID: {} found best position: {} and typos: {}", taskId, result.position(), result.typos());
        task.setPosition(result.position());
        task.setTypos(result.typos());
        task.setStatus(TaskEnum.COMPLETED);
        Thread.sleep(1000);
        taskRepository.save(task);
        log.info("Task with id: {} processed successfully", taskId);
    }

    @Scheduled(fixedRate = 1000)
    public void scheduleTaskProcessing() {
        log.debug("Checking for new tasks to process...");
        List<Task> tasksToProcess = taskRepository.findByStatus(TaskEnum.PENDING);

        for (Task task : tasksToProcess) {
            synchronized (this) {
                Task freshTask = taskRepository.findById(task.getTaskId()).orElseThrow();
                if (freshTask.getStatus() == TaskEnum.PENDING) {
                    freshTask.setStatus(TaskEnum.IN_PROGRESS);
                    taskRepository.save(freshTask);
                    CompletableFuture.runAsync(() -> {
                        try {
                            processTaskAsync(freshTask.getTaskId());
                        } catch (InterruptedException e) {
                            log.error("Something went wrong during processing task with ID: {}", freshTask.getTaskId(), e);
                        }
                    });
                }
            }
        }
    }
}
