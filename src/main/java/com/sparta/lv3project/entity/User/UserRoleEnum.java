package com.sparta.lv3project.entity.User;

public enum UserRoleEnum {

    MANAGER(Role.MANAGER),
    STAFF(Role.STAFF);

    private final String role;

    UserRoleEnum(String role) {
        this.role = role;
    }
    public String getRole() {
        return this.role;
    }
    public static class Role {
        public static final String MANAGER = "ROLE_MANAGER";
        public static final String STAFF = "ROLE_ADMIN";
    }
}
