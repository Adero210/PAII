package ceti.edu.paii.view.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

import ceti.edu.paii.R;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.model.chat;
import ceti.edu.paii.view.ChatActivity2;
import ceti.edu.paii.view.ProfileActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class matchMaking extends Fragment {

    private ProgressDialog progressDialog;
    private ListenerRegistration listenerRegistration = null;
    private FirebaseFirestore bd;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference firebaseDatabase;
    private FirebaseUser firebaseUser;
    private String chatId;
    private String uid;
    private String userName;
    private Button b;

    public matchMaking() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View r = inflater.inflate(R.layout.fragment_match_making, container, false);

        progressDialog =  new ProgressDialog(getContext());
        progressDialog.setMessage("Buscando compañero...");
        progressDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                stopLokingForChat();
                progressDialog.dismiss();
            }
        });
        progressDialog.setCancelable(false);

        b = r.findViewById(R.id.button_fragment_match);

        initfirebase();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarJugadaLibre();
            }
        });



        return r;
    }



    private void initfirebase() {
        firebaseAuth = FirebaseAuth.getInstance();
        bd = FirebaseFirestore.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseUser.getUid();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference()
                .child("user").child(firebaseAuth.getCurrentUser().getUid()).child("name");

    }

    private void buscarJugadaLibre() {
        progressDialog.setMessage("Buscando Usuario...");
        progressDialog.show();
        bd.collection("chat")
                .whereEqualTo("joiner","")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.getResult().size()==0){
                            //se crea una sala de espera en el chat

                            crearNuevaChat();

                        }else{
                            DocumentSnapshot docChat = task.getResult().getDocuments().get(0);
                            chatId = docChat.getId();
                            chat chatclass = docChat.toObject(chat.class);
                            final String started = chatclass.getStarted();
                            chatclass.setJoiner(uid);

                            bd.collection("chat")
                                    .document(chatId)
                                    .set(chatclass)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            startChat(started);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(),"Error al unirse",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
    }

    private void crearNuevaChat() {
        progressDialog.setMessage("creando...");
        progressDialog.show();

        chat newChat = new chat(uid);

        bd.collection("chat")
                .add(newChat)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        chatId = documentReference.getId();
                        //tenemos creadas la sala, debemos esperar a otro usuario

                        esperarUser();


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(),"Error aal crear",Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void esperarUser() {
        progressDialog.setMessage("Esperando Otro usuario...");

        progressDialog.show();

        listenerRegistration = bd.collection("chat")
                .document(chatId)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable final DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                        String bd = (String) documentSnapshot.get("joiner");
                        if(bd == null){
                            esperarUser();
                    }
                        else {
                            if (!bd.equals("")) {
                                final String joiner = (String) documentSnapshot.get("joiner");
                                progressDialog.dismiss();
                                progressDialog.setMessage("Ya hay un usuario");
                                progressDialog.show();
                                startChat(joiner);
                            }
                        }
                    }
                });
    }

    private void startChat(String started) {
        progressDialog.dismiss();
        if(listenerRegistration != null){
            listenerRegistration.remove();
        }
        Intent chatIntent = new Intent(getActivity(), ChatActivity2.class);
        chatIntent.putExtra(comun.EXTRA_CHAT_ID, chatId);
        chatIntent.putExtra("userId", started);
        chatIntent.putExtra("name", userName);
         startActivity(chatIntent);
         chatId = "";
    }

    public void stopLokingForChat(){
        if(listenerRegistration != null){
            listenerRegistration.remove();
            bd.collection("chat")
                    .document(chatId).delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            chatId = "";
                        }
                    });

        }
    }

    @Override
    public void onStop() {
        if(listenerRegistration != null){

        }else{
            stopLokingForChat();

        }
      /*  if (chatId != ""){
            bd.collection("chat")
                    .document(chatId).delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            chatId = "";
                        }
                    });
        }*/
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
       /* if(chatId != ""){
            esperarUser();
        }*/
    }
}
