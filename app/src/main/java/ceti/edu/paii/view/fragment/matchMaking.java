package ceti.edu.paii.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import ceti.edu.paii.R;
import ceti.edu.paii.view.match;

/**
 * A simple {@link Fragment} subclass.
 */
public class matchMaking extends Fragment {

    private Button b;

    public matchMaking() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View r = inflater.inflate(R.layout.fragment_match_making, container, false);

        b = r.findViewById(R.id.button_fragment_match);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), match.class);
                startActivity(i);
            }
        });



        return r;
    }




}
