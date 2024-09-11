package com.tvs.maintenance.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tvs.maintenance.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class BillListFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_refill_list, container, false);

        return root;
    }
}

