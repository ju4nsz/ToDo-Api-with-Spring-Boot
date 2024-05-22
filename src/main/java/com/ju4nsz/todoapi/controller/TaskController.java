package com.ju4nsz.todoapi.controller;

import com.ju4nsz.todoapi.dto.TaskCreateDTO;
import com.ju4nsz.todoapi.dto.TaskDTO;
import com.ju4nsz.todoapi.dto.UserDTO;
import com.ju4nsz.todoapi.service.ITaskService;
import com.ju4nsz.todoapi.util.WrapperResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/tasks")
public class TaskController {

    private final ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }


    /**
     *
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity<WrapperResponse<Page<TaskDTO>>> findAll(@PageableDefault(page = 0, size = 5) Pageable pageable){
        Page<TaskDTO> page = taskService.findAll(pageable);
        WrapperResponse<Page<TaskDTO>> response = new WrapperResponse<>(true, page.getTotalElements(), "tasks", page);
        return response.createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WrapperResponse<TaskDTO>> save(@RequestBody @Valid TaskCreateDTO taskCreateDTO){
        TaskDTO taskDTO = taskService.save(taskCreateDTO);
        if (taskDTO != null){
            WrapperResponse<TaskDTO> response = new WrapperResponse<>(true, 1L, "created", taskDTO);
            return response.createResponse(HttpStatus.CREATED);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WrapperResponse<TaskDTO>> findById(@PathVariable Long id){

        Optional<TaskDTO> taskDTO = taskService.findById(id);
        if (taskDTO.isPresent()){
            WrapperResponse<TaskDTO> response = new WrapperResponse<>(true, 1L, "found", taskDTO.get());
            return response.createResponse(HttpStatus.OK);
        }
        WrapperResponse<TaskDTO> response = new WrapperResponse<>(false, 0L, "not found", null);
        return response.createResponse(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WrapperResponse<String>> delete(@PathVariable Long id){
        if (taskService.delete(id)){
            WrapperResponse<String> response = new WrapperResponse<>(true, 1L, "deleted", null);
            return response.createResponse(HttpStatus.OK);
        }
        WrapperResponse<String> response = new WrapperResponse<>(true, 0L, "not found", null);
        return response.createResponse(HttpStatus.NOT_FOUND);
    }

}
