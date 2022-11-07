package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class TaskControllerTest {

    DbService dbService = mock(DbService.class);
    TaskMapper taskMapper = mock(TaskMapper.class);

    TaskController taskController = new TaskController(dbService, taskMapper);

    @Test
    void shouldReturnEmptyList(){
        //given
        when(dbService.getAllTasks()).thenReturn(List.of());
        when(taskMapper.mapToTaskDtoList(List.of())).thenReturn(List.of());

        //when
        ResponseEntity<List<TaskDto>> response = taskController.getTasks();

        //then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(List.of(), response.getBody());
    }

    @Test
    void shouldReturnTask() throws TaskNotFoundException {
        //given
        Task task = new Task(1L, "Test", "Test content");
        TaskDto taskDto = new TaskDto(1L,"Test", "Test content");

        when(dbService.getTask(1L)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //when
        ResponseEntity<TaskDto> response = taskController.getTask(1L);

        //then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void shouldEndWithOkStatus() throws TaskNotFoundException {
        //given
        doNothing().when(dbService).deleteTask(1L);

        //when
        ResponseEntity<Void> response = taskController.deleteTask(1L);

        //then
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void shouldReturnUpdatedTaskDto(){
        //given
        TaskDto inputTaskDto = new TaskDto(1L, "Test input", "Test content");
        Task mappedTask = new Task(1L, "Test input", "Test content");
        Task savedTask = new Task(1L, "Test saved", "Test content");
        TaskDto mappedToOutput = new TaskDto(1L, "Test saved", "Test content");
        when(taskMapper.mapToTask(inputTaskDto)).thenReturn(mappedTask);
        when(dbService.saveTask(mappedTask)).thenReturn(savedTask);
        when(taskMapper.mapToTaskDto(savedTask)).thenReturn(mappedToOutput);

        //when
        ResponseEntity<TaskDto> response = taskController.updateTask(inputTaskDto);

        //then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test saved", response.getBody().getTitle());
    }

    @Test
    void shouldSaveTask(){
        //given
        TaskDto inputTaskDto = new TaskDto(1L, "Test input", "Test content");
        Task mappedTask = new Task(1L, "Test input", "Test content");
        Task savedTask = new Task(1L, "Test saved", "Test content");
        when(taskMapper.mapToTask(inputTaskDto)).thenReturn(mappedTask);
        when(dbService.saveTask(mappedTask)).thenReturn(savedTask);

        //when
        ResponseEntity<Void> response = taskController.createTask(inputTaskDto);

        //then
        assertEquals(200, response.getStatusCodeValue());

    }
}