package ceti.edu.paii.comun;

        import android.util.Log;

        import java.util.Stack;

        import ceti.edu.paii.view.SessionManager;

public class comun {
    public static int ya = 0;
    public static String userName;
    public static String getId;
    public static final String EXTRA_CHAT_ID = "chatId";
    public static SessionManager sessionManager;
    public static String URL = "http://192.168.0.5/";

    public static String aleatorio(int numerosPreuntas){
        // AleatoriSinRepeticion();
        String num = "";
        int pos;
        int nCartas = numerosPreuntas;
        Stack< Integer > pCartas = new Stack < Integer > ();
        for (int i = 0; i < nCartas ; i++) {
            pos = (int) Math.floor(Math.random() * nCartas );
            while (pCartas.contains(pos)) {
                pos = (int) Math.floor(Math.random() * nCartas );
            }

            pCartas.push(pos);
            num = String.valueOf(pos);
        }
        Log.i("Numeros",pCartas.toString());

        return num;
    }

}
