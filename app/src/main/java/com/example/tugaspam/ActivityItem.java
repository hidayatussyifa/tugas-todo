package com.example.tugaspam;

public class ActivityItem {
    private int id;
    private String what;
    private String time;

    public ActivityItem(int id, String what, String time) {
        this.id = id;
        this.what = what;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getWhat() {
        return what;
    }

    public String getTime() {
        return time;
    }
}

