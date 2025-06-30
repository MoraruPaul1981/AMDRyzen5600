package com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.View.Fragments.viewpager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.dsy.dsu.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogoFragmentTwo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogoFragmentTwo extends Fragment {

    // TODO: Rename parameter arguments, choose names that match


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LogoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LogoFragmentTwo newInstance(String param1, String param2) {
        LogoFragmentTwo fragment = new LogoFragmentTwo();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logotwo, container, false);
    }
}