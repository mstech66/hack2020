package com.epam.model;

public class Data {
    private final int id;
    private final Data parentCategory;
    private final String name;

    public Data(int id, Data parentCategory, String name) {
        this.id = id;
        this.parentCategory = parentCategory;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Data getParentCategory() {
        return parentCategory;
    }

    public String getName() {
        return name;
    }
}
