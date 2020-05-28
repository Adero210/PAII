package ceti.edu.paii.model;


import java.util.Date;

public class chat {
    private Date created;
    private String joiner;
    private String started;
    private boolean movile;

    public chat() {
    }

    public chat(String started) {
        this.started = started;
        this.joiner = "";
        this.created = new Date();
        this.movile = true;
    }

    public chat(Date created, String joiner, String started, boolean movile) {
        this.created = created;
        this.joiner = joiner;
        this.started = started;
        this.movile =  movile;
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

    public boolean isMovile() {
        return movile;
    }

    public void setMovile(boolean movile) {
        this.movile = movile;
    }
}
