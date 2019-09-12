package ceti.edu.paii.model;

public class Message {
    private String message;
    private User sender;
    private long createdAr;

    public Message() {
        this.message = message;
        this.sender = sender;
        this.createdAr = createdAr;
    }

    public String getMessage() {
        return message;
    }

    public User getSender() {
        return sender;
    }

    public long getCreatedAr() {
        return createdAr;
    }

    public class User {
        String nickName;
        String profileUrl;

        public User(String nickName, String profileUrl) {
            this.nickName = nickName;
            this.profileUrl = profileUrl;
        }

        public String getNickName() {
            return nickName;
        }

        public String getProfileUrl() {
            return profileUrl;
        }
    }
}


