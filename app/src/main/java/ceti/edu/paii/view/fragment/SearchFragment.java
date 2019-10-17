package ceti.edu.paii.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import ceti.edu.paii.MainActivity;
import ceti.edu.paii.R;
import ceti.edu.paii.adapter.MessageListAdapter;
import ceti.edu.paii.adapter.SectionPagerAdapter;
import ceti.edu.paii.comun.comun;
import ceti.edu.paii.model.Message;
import ceti.edu.paii.model.UserMessage;
import ceti.edu.paii.view.ContainerActivity;
import ceti.edu.paii.view.SessionManager;

/*
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private ViewPager viewPager;
    private SectionPagerAdapter mSpA;

    private TabLayout mTabLayout;
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        showToolbar(getResources().getString(R.string.Chat), false, view);




        viewPager = view.findViewById(R.id.message_tabpager);
        mSpA = new SectionPagerAdapter(getFragmentManager());

        viewPager.setAdapter(mSpA);
        viewPager.setCurrentItem(1);
        viewPager.setPageTransformer(true, new RotateUpTransformer());

        mTabLayout = view.findViewById(R.id.message_Tablayout);
        mTabLayout.setupWithViewPager(viewPager);


        return view;

    }

    public void showToolbar(String tittle, boolean upButton,View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
