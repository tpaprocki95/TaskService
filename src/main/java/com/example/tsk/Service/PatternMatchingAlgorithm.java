package com.example.tsk.Service;

import com.example.tsk.Model.TaskResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PatternMatchingAlgorithm {

    public static TaskResult findBestMatch(String pattern, String input) {
        int bestPosition = -1;
        int leastTypos = Integer.MAX_VALUE;

        for (int i = 0; i <= input.length() - pattern.length(); i++) {
            String currentSubstring = input.substring(i, i + pattern.length());
            int typos = countTypos(currentSubstring, pattern);

            if (typos < leastTypos) {
                leastTypos = typos;
                bestPosition = i;
            } else if (typos == leastTypos) {
                bestPosition = Math.min(bestPosition, i);
            }
        }

        if (leastTypos == Integer.MAX_VALUE) {
            return new TaskResult(-1, -1);
        }
        return new TaskResult(bestPosition, leastTypos);
    }

    private static int countTypos(String str1, String pattern) {
        int typos = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != pattern.charAt(i)) {
                typos++;
            }
        }
        if (typos == pattern.length()) {
            return Integer.MAX_VALUE;
        } else {
            return typos;
        }
    }
}



