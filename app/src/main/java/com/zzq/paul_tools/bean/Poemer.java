package com.zzq.paul_tools.bean;

import java.io.Serializable;

/**
 * @author zhuzaiqing
 * @describe  诗人
 * @time 2018/11/2 17:47
 */
public class Poemer implements Serializable {
    private String author;
    private String origin;
    private String category;
    private String content;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
