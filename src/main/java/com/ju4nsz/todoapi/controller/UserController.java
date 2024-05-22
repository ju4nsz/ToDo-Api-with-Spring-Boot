package com.ju4nsz.todoapi.controller;

import com.ju4nsz.todoapi.dto.UserCreateDto;
import com.ju4nsz.todoapi.dto.UserDTO;
import com.ju4nsz.todoapi.service.IUserService;
import com.ju4nsz.todoapi.util.WrapperResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final IUserService iUserService;

    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @GetMapping
    public ResponseEntity<WrapperResponse<Page<UserDTO>>> findAll(Pageable pageable) {
        Page<UserDTO> page = iUserService.findAll(pageable);
        WrapperResponse<Page<UserDTO>> response = new WrapperResponse<>(true, page.getTotalElements(), "data", page);
        return response.createResponse(HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<WrapperResponse<UserDTO>> save(@RequestBody @Valid UserCreateDto userCreateDto){
        UserDTO userDTO = iUserService.save(userCreateDto);
        WrapperResponse<UserDTO> response = new WrapperResponse<>(true, 1L, "created", userDTO);
        return response.createResponse(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WrapperResponse<UserDTO>> findById(@PathVariable Long id){
        Optional<UserDTO> userDTO = iUserService.findById(id);
        if (userDTO.isPresent()){
            WrapperResponse<UserDTO> response = new WrapperResponse<>(true, 1L, "found", userDTO.get());
            return response.createResponse(HttpStatus.OK);
        }
        WrapperResponse<UserDTO> response = new WrapperResponse<>(false, 0L, "not found", null);
        return response.createResponse(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WrapperResponse<String>> delete(@PathVariable Long id){
        boolean deleted = iUserService.delete(id);

        if (deleted){
            WrapperResponse<String> response = new WrapperResponse<>(true, 1L, "deleted", null);
            return response.createResponse(HttpStatus.OK);
        }
        WrapperResponse<String> response = new WrapperResponse<>(true, 0L, "not found", null);
        return response.createResponse(HttpStatus.NOT_FOUND);
    }

}
