package ceti.edu.paii.model;


import java.util.Date;

public class chat {
    private Date created;
    private String joiner;
    private String started;

    public chat() {
    }

    public chat(String started) {
        this.started = started;
        this.joiner = "";
        this.created = new Date();
    }

    public chat(Date created, String joiner, String started) {
        this.created = created;
        this.joiner = joiner;
        this.started = started;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getJoiner() {
        return joiner;
    }

    public void setJoiner(String joiner) {
        this.joiner = joiner;
    }

    public String getStarted() {
        return started;
    }

    public void setStarted(String started) {
        this.started = started;
    }
}
