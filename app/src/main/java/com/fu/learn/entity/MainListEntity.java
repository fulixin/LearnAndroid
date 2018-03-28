package com.fu.learn.entity;

/**
 * Created by fulixin on 2018/3/2.
 */

public class MainListEntity extends BaseEntity {
    private String title;
    private String note;
    private String className;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
