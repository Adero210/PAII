package ceti.edu.paii.model;

public class Picture {

    private String picture;
    private String userName;

    public Picture(String picture, String userName) {
        this.picture = picture;
        this.userName = userName;

    }


    public String getPicture() {
        return picture;
    }

    public String getUserName() {
        return userName;
    }
}