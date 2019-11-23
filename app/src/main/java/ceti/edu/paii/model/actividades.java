package ceti.edu.paii.model;

public class actividades {

    String pregunta;
    String respuestac;
    String opcionA;
    String opcionB;
    String opcionC;
    String opcionD;
    String parrafo;
    String urlImagen;
    String urlAudio;
    String textImagen;

    public actividades() {
    }

    public actividades(String pregunta, String respuestac, String opcionA, String opcionB, String opcionC, String opcionD, String parrafo, String urlImagen, String urlAudio, String textImagen) {
        this.pregunta = pregunta;
        this.respuestac = respuestac;
        this.opcionA = opcionA;
        this.opcionB = opcionB;
        this.opcionC = opcionC;
        this.opcionD = opcionD;
        this.parrafo = parrafo;
        this.urlImagen = urlImagen;
        this.urlAudio = urlAudio;
        this.textImagen = textImagen;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuestac() {
        return respuestac;
    }

    public void setRespuestac(String respuestac) {
        this.respuestac = respuestac;
    }

    public String getOpcionA() {
        return opcionA;
    }

    public void setOpcionA(String opcionA) {
        this.opcionA = opcionA;
    }

    public String getOpcionB() {
        return opcionB;
    }

    public void setOpcionB(String opcionB) {
        this.opcionB = opcionB;
    }

    public String getOpcionC() {
        return opcionC;
    }

    public void setOpcionC(String opcionC) {
        this.opcionC = opcionC;
    }

    public String getOpcionD() {
        return opcionD;
    }

    public void setOpcionD(String opcionD) {
        this.opcionD = opcionD;
    }

    public String getParrafo() {
        return parrafo;
    }

    public void setParrafo(String parrafo) {
        this.parrafo = parrafo;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getUrlAudio() {
        return urlAudio;
    }

    public void setUrlAudio(String urlAudio) {
        this.urlAudio = urlAudio;
    }

    public String getTextImagen() {
        return textImagen;
    }

    public void setTextImagen(String textImagen) {
        this.textImagen = textImagen;
    }
}
