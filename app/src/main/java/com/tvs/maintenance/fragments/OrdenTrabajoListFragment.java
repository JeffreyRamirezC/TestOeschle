package com.tvs.maintenance.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tvs.maintenance.R;
import com.tvs.maintenance.activities.OrdenTrabajoMaintenanceActivity;
import com.tvs.maintenance.util.constants.Constantes;
import com.tvs.maintenance.util.ui.AdapterOrdenTrabajo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.tvs.maintenance.util.ui.fab.FloatingActionMenu;

public class OrdenTrabajoListFragment extends Fragment implements View.OnClickListener {
    public static int TAG_NEW_ORDEN = 820;
    public static int TAG_EDIT_ORDEN = 720;
    ListView lv;
    FloatingActionMenu fam;
    Dialog dialog;
    LinearLayout maintenance,tool,warranty;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        reloadList();
    }

    void reloadList() {
        AdapterOrdenTrabajo aot = new AdapterOrdenTrabajo(getContext(),getActivity());
        lv.setAdapter(aot);
        lv.deferNotifyDataSetChanged();

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_ot_list, container, false);
        dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_ot_type);
        maintenance=dialog.findViewById(R.id.dialog_button_maintenance);
        tool=dialog.findViewById(R.id.dialog_button_tool);
        warranty=dialog.findViewById(R.id.dialog_button_warranty);
        maintenance.setClickable(true);
        tool.setClickable(true);
        warranty.setClickable(true);
        maintenance.setOnClickListener(this);
        tool.setOnClickListener(this);
        warranty.setOnClickListener(this);

        dialog.create();

        lv = root.findViewById(R.id.lv_ot);
        reloadList();
        root.findViewById(R.id.fab_new_ot).setOnClickListener(this);
        lv.setOnItemClickListener(null);
        return root;
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.fab_new_ot) {
            if (Constantes.CurrentUser == Constantes.MenuSTA) {
                Intent i = new Intent(getActivity(), OrdenTrabajoMaintenanceActivity.class);
                i.putExtra("n_ot", ++Constantes.orden);
                startActivityForResult(i, TAG_NEW_ORDEN);
            } else {
                dialog.show();
            }
        }
        if(v.getId()==maintenance.getId()){
            dialog.dismiss();
            Intent i = new Intent(getActivity(), OrdenTrabajoMaintenanceActivity.class);
            i.putExtra("n_ot", ++Constantes.orden);
            startActivityForResult(i, TAG_NEW_ORDEN);
        }
        if(v.getId()==tool.getId()){
            dialog.dismiss();
            Intent i = new Intent(getActivity(), OrdenTrabajoMaintenanceActivity.class);
            i.putExtra("n_ot", ++Constantes.orden);
            startActivityForResult(i, TAG_NEW_ORDEN);
        }

        if(v.getId()==warranty.getId()){
            dialog.dismiss();
            Intent i = new Intent(getActivity(), OrdenTrabajoMaintenanceActivity.class);
            i.putExtra("n_ot", ++Constantes.orden);
            startActivityForResult(i, TAG_NEW_ORDEN);
        }


    }
}
