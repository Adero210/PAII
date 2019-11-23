package ceti.edu.paii.model;



public class Challenge {

    public String opener;
    public String gameRef;

    public Challenge() {
    }

    public Challenge(String opener, String gameRef) {
        this.gameRef = gameRef;
        this.opener = opener;
    }

    public String getOpener() {
        return opener;
    }

    public void setOpener(String opener) {
        this.opener = opener;
    }

    public String getGameRef() {
        return gameRef;
    }

    public void setGameRef(String gameRef) {
        this.gameRef = gameRef;
    }
}
