package ceti.edu.paii.model;

public class UserMessage {
    private String message;
    private static User sender;
    private static long createdAr;
    String profileUrl;



    public String getMessage() {
        return message;
    }

    public static User getSender() {
        return sender;
    }

    public static long getCreatedAr() {
        return createdAr;
    }

    public String getProfileUrl() {
            return profileUrl;
    }
}
