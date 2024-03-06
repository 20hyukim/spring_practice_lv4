package com.sparta.lv3project.entity.User;

public enum UserDepartmentEnum {
    CURRICULUM(Department.CURRICULUM),
    MARKETING(Department.MARKETING),
    DEVELOPMENT(Department.DEVELOPMENT);

    private final String department;

    UserDepartmentEnum(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return this.department;
    }

    public static class Department {
        public static final String CURRICULUM = "ROLE_CURRICULUM";
        public static final String MARKETING = "ROLE_MARKETING";
        public static final String DEVELOPMENT = "ROLE_DEVELOPMENT";
    }
}

