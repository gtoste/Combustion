package com.example.combustion;

public class StatisticModel {
    private int image_id;
    private String title;
    private String content;

    public StatisticModel(int image_id, String title, String content) {
        this.image_id = image_id;
        this.title = title;
        this.content = content;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
