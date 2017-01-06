package com.ewebs.hotfood.model;

/**
 * Created by Paul on 2017/1/4.
 */

public class ShopData {

    private String name,info,location;
    private int star;

    public ShopData(String name, String info, String location, int star) {
        this.name = name;
        this.info = info;
        this.location = location;
        this.star = star;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public String getLocation() {
        return location;
    }

    public int getStar() {
        return star;
    }
}
