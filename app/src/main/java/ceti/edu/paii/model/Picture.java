package ceti.edu.paii.model;

public class Picture {

    private String picture;
    private String userName;
    private String porcentaje;


    public Picture(String picture, String userName, String porcentaje) {
        this.picture = picture;
        this.userName = userName;
        this.porcentaje = porcentaje;
    }

    public String getPicture() {
        return picture;
    }

    public String getUserName() {
        return userName;
    }

    public String getPorcentaje() {
        return porcentaje;
    }
}
