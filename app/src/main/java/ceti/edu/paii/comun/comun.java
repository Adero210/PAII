package ceti.edu.paii.comun;

        import android.support.v4.app.FragmentManager;
        import android.util.Log;

        import com.android.volley.toolbox.JsonArrayRequest;
        import com.google.gson.JsonArray;
        import com.google.gson.JsonObject;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.Stack;

        import ceti.edu.paii.view.SessionManager;

public class comun {
    public static int ya = 0;
    public static String userName;
    public static String getId;
    public static final String EXTRA_CHAT_ID = "chatId";
    public static SessionManager sessionManager;
    public static String URL = "http://192.168.0.2/PAI_php/conexiones/";

    public static String currentId;
    public static FragmentManager fragmentManager;

    public static  String MESSAGE_BOTTON_SHEET="";
    public static String userNameLec;
    public static int lessonCalis;

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

    public static class Optionss{
        public static String opcA;
        public static String opcB;
        public static String opcC;
        public static String opcD;
    }

    public static class Images{
        public static String imageUno;
        public static String imageTwo;
        public static String imageThree;
        public static String imagefour;
    }

    public static Optionss getOptions(JSONArray options){
        Optionss newOPtions = null;

        for (int i = 0; i < options.length();i++){
            try {
                JSONObject option = options.getJSONObject(i);
                if (i==0){
                    newOPtions.opcA = option.getString("name");
                }
                if (i==1){
                    newOPtions.opcB = option.getString("name");
                }
                if (i==2){
                    newOPtions.opcC = option.getString("name");
                }
                if (i==3){
                    newOPtions.opcD = option.getString("name");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return newOPtions;
    }

    public static Images getImages(JSONArray images){

        Images newImages = null;

        for (int i = 0; i < images.length();i++){
            try {
                JSONObject option = images.getJSONObject(i);
                if (i==0){
                    newImages.imageUno = option.getString("imageRoute");
                }
                if (i==1){
                    newImages.imageTwo = option.getString("imageRoute");
                }
                if (i==2){
                    newImages.imageThree = option.getString("imageRoute");
                }
                if (i==3){
                    newImages.imagefour = option.getString("imageRoute");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return newImages;
    }
}
