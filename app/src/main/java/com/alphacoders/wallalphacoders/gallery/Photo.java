package com.alphacoders.wallalphacoders.gallery;

/**
 * Created by alextcy on 01.10.14.
 *
 */
public class Photo {

    private int id;
    //ссылка на миниатюру
    private String thumbUrl;
    //ссылка на увеличеную миниатюру
    private String thumbBigUrl;
    //ссылка на оригинальную фотографию
    private String originalUrl;
    //размеры оригинальной картинки
    private int width;
    private int height;
    //название категории в которой находится картинка
    private String categoryName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getThumbBigUrl() {
        return thumbBigUrl;
    }

    public void setThumbBigUrl(String thumbBigUrl) {
        this.thumbBigUrl = thumbBigUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String toString()
    {
        return originalUrl;
    }
}
