package com.ju4nsz.todoapi.util;

import com.ju4nsz.todoapi.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WrapperResponse<T> {

    private boolean status;
    private Long rowCount;
    private String message;
    private T body;

    public WrapperResponse() {
    }

    public WrapperResponse(boolean status, Long rowCount, String message, T body) {
        this.status = status;
        this.rowCount = rowCount;
        this.message = message;
        this.body = body;
    }

    public ResponseEntity<WrapperResponse<T>> createResponse(){
        return new ResponseEntity<>(this, HttpStatus.OK);
    }

    public ResponseEntity<WrapperResponse<T>> createResponse(HttpStatus status){
        return new ResponseEntity<>(this, status);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getRowCount() {
        return rowCount;
    }

    public void setRowCount(Long rowCount) {
        this.rowCount = rowCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
