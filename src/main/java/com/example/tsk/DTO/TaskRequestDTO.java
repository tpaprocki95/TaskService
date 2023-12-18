package com.example.tsk.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
public class TaskRequestDTO {
    private final String input;
    private final String pattern;
}
