package com.tvs.maintenance.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tvs.maintenance.R;
import com.tvs.maintenance.activities.OrdenTrabajoMaintenanceActivity;
import com.tvs.maintenance.util.ui.barcode.BarcodeReaderActivity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

public class VehicleFragment extends Fragment {
    EditText ot_chasis;
    OrdenTrabajoMaintenanceActivity activity;
    public final int TAG_READ_CHASIS=101;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        activity=(OrdenTrabajoMaintenanceActivity) getActivity();
        View root = inflater.inflate(R.layout.fragment_vehicle_info, container, false);
        ot_chasis=root.findViewById(R.id.ot_chasis);
        ot_chasis.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (ot_chasis.getRight() - ot_chasis.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        //activity.handleSearchClientDialog(ot_dni.getText().toString());
                        Intent i=new Intent(activity, BarcodeReaderActivity.class);
                        startActivityForResult(i,TAG_READ_CHASIS);
                        return true;
                    }
                }
                return false;
            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==TAG_READ_CHASIS){
            if(resultCode==getActivity().RESULT_OK){

            }
        }
    }
}
