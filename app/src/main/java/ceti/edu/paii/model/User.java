package ceti.edu.paii.model;

public class User extends UserMessage {
    String idUser;
    String nickName;

    public User(String idUser, String nickName) {
        this.idUser = idUser;
        this.nickName = nickName;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getNickName() {
        return nickName;
    }
}
