package com.tvs.maintenance.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.google.android.material.tabs.TabLayout;
import com.tvs.maintenance.R;
import com.tvs.maintenance.util.ui.AdapterViewPager;
import com.tvs.maintenance.util.ui.SelectiveViewPager;
import com.tvs.maintenance.util.ui.fab.FloatingActionMenu;
import com.tvs.maintenance.util.ui.fab.Util;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class OrdenTrabajoFragment extends Fragment{
    FloatingActionMenu fam;
    private String TAG="";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
     //   gotopage=getActivity().getIntent().getExtras().getInt("goto");
        View root = inflater.inflate(R.layout.fragment_container_ot, container, false);


        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==getActivity().RESULT_OK){
            switch(requestCode){

            }
        }
    }


}
