package com.example.tsk.Service;

import com.example.tsk.DTO.TaskRequestDTO;
import com.example.tsk.DTO.TaskResponseDTO;
import com.example.tsk.Enums.TaskEnum;
import com.example.tsk.Exceptions.TaskNotFoundException;
import com.example.tsk.Model.Task;
import com.example.tsk.TaskRepository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        Task newTask = new Task();
        newTask.setInput(taskRequestDTO.getInput());
        newTask.setPattern(taskRequestDTO.getPattern());
        newTask.setStatus(TaskEnum.PENDING);
        newTask.setTypos(-1);
        newTask.setPosition(-1);

        Task savedTask = taskRepository.save(newTask);

        return modelMapper.map(savedTask, TaskResponseDTO.class);
    }

    public TaskResponseDTO getTaskById(Long Id) {
        return taskRepository.findById(Id)
                .map(o -> modelMapper.map(o, TaskResponseDTO.class))
                .orElseThrow(() -> new TaskNotFoundException(Id));
    }

    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(o -> modelMapper.map(o, TaskResponseDTO.class))
                .toList();
    }
}
