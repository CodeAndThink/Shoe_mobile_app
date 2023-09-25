package com.example.shoe_store.data;

public class NotificationData {
    private String time;
    private String content;

    public NotificationData(String time, String content, String status) {
        this.time = time;
        this.content = content;
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "NotificationData{" +
                "time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status = "unread";
}
