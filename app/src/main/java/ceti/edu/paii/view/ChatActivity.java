package ceti.edu.paii.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;

import ceti.edu.paii.R;
import ceti.edu.paii.adapter.SectionPagerAdapter;

public class ChatActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SectionPagerAdapter mSpA;

    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        showToolbar(getResources().getString(R.string.Chat),true);

        viewPager = findViewById(R.id.message_tabpager_activity);
        mSpA = new SectionPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mSpA);
        viewPager.setCurrentItem(1);
        viewPager.setPageTransformer(true, new RotateUpTransformer());

        mTabLayout = findViewById(R.id.message_Tablayout_activity);
        mTabLayout.setupWithViewPager(viewPager);
    }

    public void showToolbar(String tittle, boolean upButton) {
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }
}
