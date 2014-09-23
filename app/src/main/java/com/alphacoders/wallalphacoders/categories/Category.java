package com.alphacoders.wallalphacoders.categories;

/**
 * Created by alextcy on 22.09.14.
 */
public class Category {

    private int id;
    private String name;
    private String image;

    public Category(int catId, String catName, String catImage)
    {
        id      = catId;
        name    = catName;
        image   = catImage;
    }

    public int getId() {
        return id;
    }

    /*public void setId(int id) {
        this.id = id;
    }*/

    public String getName() {
        return name;
    }

    /*public void setName(String name) {
        this.name = name;
    }*/

    public String getImage()
    {
        return image;
    }


}
