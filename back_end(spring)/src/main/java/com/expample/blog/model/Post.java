package com.expample.blog.model;

public class Post {
    private String title;
    private String desc;

    public Post() {}

    public Post(String title, String desc) {
        setTitle(title);
        setDesc(desc);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}
