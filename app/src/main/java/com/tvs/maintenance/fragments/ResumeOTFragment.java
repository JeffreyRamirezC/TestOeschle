package com.tvs.maintenance.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.vision.text.Line;
import com.tvs.maintenance.R;
import com.tvs.maintenance.util.ui.fab.FloatingActionMenu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ResumeOTFragment extends Fragment implements View.OnClickListener{

    FloatingActionMenu fam;
    private String TAG="";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //   gotopage=getActivity().getIntent().getExtras().getInt("goto");
        View root = inflater.inflate(R.layout.fragment_resume_ot, container, false);

        root.findViewById(R.id.ic_inspection).setOnClickListener(this);
        root.findViewById(R.id.ic_info_cliente).setOnClickListener(this);
        root.findViewById(R.id.ic_info_vehicle).setOnClickListener(this);
        root.findViewById(R.id.ic_jobs).setOnClickListener(this);
        root.findViewById(R.id.ic_inspection).setClickable(true);
        root.findViewById(R.id.ic_info_cliente).setClickable(true);
        root.findViewById(R.id.ic_info_vehicle).setClickable(true);
        root.findViewById(R.id.ic_jobs).setClickable(true);

        return root;
    }

    @Override
    public void onClick(View v) {
       LinearLayout temp_c=((LinearLayout)((LinearLayout)v.getParent().getParent()).getChildAt(1));
       temp_c.setVisibility(temp_c.getVisibility()== View.VISIBLE?View.GONE:View.VISIBLE);
    }
}
