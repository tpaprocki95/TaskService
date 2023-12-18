package com.example.tsk.DTO;

import com.example.tsk.Enums.TaskEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskResponseDTO {
    private Long id;
    private String input;
    private String pattern;
    private TaskEnum status;
    private Integer position;
    private Integer typos;
}
