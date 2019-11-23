package ceti.edu.paii.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ceti.edu.paii.view.fragment.ChatsFragment;
import ceti.edu.paii.view.fragment.FriendsFragment;
import ceti.edu.paii.view.fragment.RequestsFragment;
import ceti.edu.paii.view.fragment.UsersFragemts;
import ceti.edu.paii.view.fragment.matchMaking;

public class SectionPagerAdapter extends FragmentPagerAdapter {
    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                RequestsFragment requestsFragment = new RequestsFragment();
                return requestsFragment;

            case 1:
                ChatsFragment chatsFragment = new ChatsFragment();
                return chatsFragment;

            case 2:
                FriendsFragment friendsFragment = new FriendsFragment();
                return friendsFragment;

            case 3:
                matchMaking usersFragemts = new matchMaking();
                return usersFragemts;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    public CharSequence getPageTitle(int position){

        switch (position){
            case 0:
                return "Solicitudes";
            case 1:
                return "Chat";
            case 2:
                return "Lista de Amigos";
            case 3:
                return "Buscar";
            default:
                return null;
        }
    }
}
