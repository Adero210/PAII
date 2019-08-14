package ceti.edu.paii.view.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ceti.edu.paii.R;
import ceti.edu.paii.adapter.MessageListAdapter;
import ceti.edu.paii.adapter.SectionPagerAdapter;
import ceti.edu.paii.model.Message;
import ceti.edu.paii.model.UserMessage;

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
        showToolbar(getResources().getString(R.string.Chat),false,view);
        viewPager =  view.findViewById(R.id.message_tabpager);
        mSpA = new SectionPagerAdapter(getActivity().getSupportFragmentManager());


        viewPager.setAdapter(mSpA);

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
