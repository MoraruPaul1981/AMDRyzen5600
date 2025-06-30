package com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.Model.bl_viewpager2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.View.Fragments.fragmentlogoswipes.LogoFragment;
import com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.View.Fragments.fragmentlogoswipes.LogoFragmentTree;
import com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.View.Fragments.fragmentlogoswipes.LogoFragmentTwo;

public class DemoCollectionAdapter extends FragmentStateAdapter {
    public DemoCollectionAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int).
        Fragment fragment = null;
        Bundle args;
        switch (position){
            case 0:
                  fragment = new LogoFragment();
                  args = new Bundle();
                // The object is just an integer.
                fragment.setArguments(args);
                break;
            case 1:
                fragment = new LogoFragmentTwo();
                  args = new Bundle();
                // The object is just an integer.
                fragment.setArguments(args);
                break;


            case 2:
                fragment = new LogoFragmentTree();
                args = new Bundle();
                // The object is just an integer.
                fragment.setArguments(args);
                break;

            
        }


        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
