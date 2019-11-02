package ceti.edu.paii.model;

public class Message {
    private String message;
    private String type;
    private String from;
    private String urlFoto;
    private String urlAudio;

    private long time;
    private boolean seen;

    public Message() {
    }



    public Message(String message, String type, String from, long time, boolean seen) {
        this.message = message;
        this.type = type;
        this.from = from;
        this.time = time;
        this.seen = seen;
    }

    public Message(String type, String from, String urlAudio, long time) {
        this.type = type;
        this.from = from;
        this.urlFoto = urlAudio;
        this.time = time;
    }

    public Message(String message, String type, String from, String urlFoto, long time, boolean seen) {
        this.message = message;
        this.type = type;
        this.from = from;
        this.urlFoto = urlFoto;
        this.time = time;
        this.seen = seen;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public String getFrom() {
        return from;
    }



    public void setFrom(String from) {
        this.from = from;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getUrlAudio() {
        return urlAudio;
    }

    public void setUrlAudio(String urlAudio) {
        this.urlAudio = urlAudio;
    }
}


