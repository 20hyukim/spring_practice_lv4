package com.sparta.lv3project.entity.Lecture;

public enum LectureCategoryEnum {
    SPRING(Category.SPRING),
    REACT(Category.REACT),
    NODE(Category.NODE);

    private final String category;

    LectureCategoryEnum(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }


    public static class Category {
        public static final String SPRING = "ROLE_SPRING";
        public static final String REACT = "ROLE_REACT";
        public static final String NODE = "ROLE_NODE";
    }
}
