package com.tvs.maintenance.util.ui;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tvs.maintenance.R;
import com.tvs.maintenance.activities.OrdenTrabajoMaintenanceActivity;
import com.tvs.maintenance.fragments.OrdenTrabajoListFragment;
import com.tvs.maintenance.util.constants.Constantes;

import java.util.ArrayList;
import java.util.List;

public class AdapterOrdenTrabajo extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<String> arreglo;
    private Activity activity;
    public AdapterOrdenTrabajo(Context context, Activity activity){
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        arreglo=new ArrayList<>();
        this.activity=activity;
       for(int i = 0; i< Constantes.orden; i++){
           arreglo.add("l");
       }
    }

    @Override
    public int getCount() {
        return arreglo.size();
    }

    @Override
    public Object getItem(int i) {
        return arreglo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_ot, parent, false);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, OrdenTrabajoMaintenanceActivity.class);
                i.putExtra("n_ot", Constantes.orden);
                activity.startActivityForResult(i, OrdenTrabajoListFragment.TAG_EDIT_ORDEN);
            }
        });
        return convertView;
    }
    @Override
    public boolean isEnabled(int position)
    {
        return true;
    }
}