package com.tvs.maintenance.fragments;

import android.app.ProgressDialog;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.util.Strings;
import com.tvs.maintenance.R;
import com.tvs.maintenance.activities.OrdenTrabajoMaintenanceActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ClientFragment extends Fragment {
    EditText ot_dni;
    OrdenTrabajoMaintenanceActivity activity;
    ProgressDialog pd;
    ListView listView;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        activity=(OrdenTrabajoMaintenanceActivity) getActivity();
        super.onActivityCreated(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        activity=(OrdenTrabajoMaintenanceActivity) getActivity();
        pd=new ProgressDialog(getContext());
        pd.setMessage("obteniendo cliente");
        pd.setIndeterminate(true);
        pd.create();
        View root = inflater.inflate(R.layout.fragment_client_info, container, false);
        listView=root.findViewById(R.id.dinamic_filter);
        ot_dni=root.findViewById(R.id.ot_dni_search);
        ot_dni.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                editable.toString();

            }
        });

       /*
        ot_dni.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (ot_dni.getRight() - ot_dni.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width())) {
                        // your action here
                        activity.handleSearchClientDialog(ot_dni.getText().toString());
                        return true;
                    }
                }
                return false;
            }
        });
        */

        return root;
    }
    private class doSearchByName extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            super.onPostExecute(s);
        }
    }

}
