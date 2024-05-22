package com.ju4nsz.todoapi.util;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Roles {

    USER(Arrays.asList(Permissions.CREATE_TASK, Permissions.DELETE_TASK, Permissions.MODIFY_TASK, Permissions.READ_TASK, Permissions.READ_USER)),
    ADMINISTRATOR(Arrays.asList(Permissions.CREATE_TASK, Permissions.DELETE_TASK, Permissions.MODIFY_TASK, Permissions.READ_ALL_TASKS, Permissions.READ_ALL_USERS, Permissions.READ_TASK, Permissions.READ_USER, Permissions.DELETE_USER));

    private List<Permissions> permissions;

    Roles(List<Permissions> permissions) {
        this.permissions = permissions;
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }

    void setPermissions(List<Permissions> permissions) {
        this.permissions = permissions;
    }
}
