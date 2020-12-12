package com.gjxaiou;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    int id;
    String name;
    int count;

    public Product(int id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name=" + name +
                ", count=" + count +
                '}';
    }
}
