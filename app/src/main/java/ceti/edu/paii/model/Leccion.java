package ceti.edu.paii.model;

public class Leccion {

    private String leccionBack;
    private String leccionWord;
    private String leccionNumber;
    private String leccionLenguaje;
    private String porcentajeWord;
    private String porcentajeNumber = "0";

    public Leccion(String leccionBack, String leccionWord, String leccionNumber, String leccionLenguaje, String porcentajeWord, String porcentajeNumber) {
        this.leccionBack = leccionBack;
        this.leccionWord = leccionWord;
        this.leccionNumber = leccionNumber;
        this.leccionLenguaje = leccionLenguaje;
        this.porcentajeWord = porcentajeWord;
        this.porcentajeNumber = porcentajeNumber;
    }

    public String getLeccionBack() {
        return leccionBack;
    }

    public String getLeccionWord() {
        return leccionWord;
    }

    public String getLeccionNumber() {
        return leccionNumber;
    }

    public String getLeccionLenguaje() {
        return leccionLenguaje;
    }

    public String getPorcentajeWord() {
        return porcentajeWord;
    }

    public String getPorcentajeNumber() {
        return porcentajeNumber;
    }

}
