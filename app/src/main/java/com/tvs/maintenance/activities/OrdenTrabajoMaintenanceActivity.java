package com.tvs.maintenance.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.tvs.maintenance.R;
import com.tvs.maintenance.fragments.BillListFragment;
import com.tvs.maintenance.fragments.ClientFragment;
import com.tvs.maintenance.fragments.ListJobsFragment;
import com.tvs.maintenance.fragments.RefillFragment;
import com.tvs.maintenance.fragments.ResumeOTFragment;
import com.tvs.maintenance.fragments.VehicleFragment;
import com.tvs.maintenance.fragments.VehicleIntroDocFragment;
import com.tvs.maintenance.util.ui.fab.FloatingActionMenu;
import com.tvs.maintenance.util.ui.fab.Util;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class OrdenTrabajoMaintenanceActivity extends AppCompatActivity implements View.OnClickListener{
    FloatingActionMenu fam;
    private String TAG="";
    ImageView saveButton;
    int current_Tag=-1;
    Dialog client_dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden_trabajo_maintenance);
        int _val=getIntent().getExtras().getInt("n_ot");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Orden de Trabajo Nº "+ _val);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        Util util = new Util();

        fam=findViewById(R.id.fab_menu);
        util.createCustomAnimation(fam,R.drawable.ic_ot_edit);
        findViewById(R.id.fab_home).setOnClickListener(this);
        findViewById(R.id.fab_info_cliente).setOnClickListener(this);
        findViewById(R.id.fab_info_vehiculo).setOnClickListener(this);
        findViewById(R.id.fab_inspeccion).setOnClickListener(this);
        findViewById(R.id.fab_trabajos_realizados).setOnClickListener(this);
        findViewById(R.id.fab_repuestos).setEnabled(false);
        findViewById(R.id.fab_repuestos).setOnClickListener(this);
        findViewById(R.id.fab_cotizaciones).setOnClickListener(this);
        findViewById(R.id.fab_cotizaciones).setEnabled(false);
        saveButton=findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeSave(current_Tag);
            }
        });
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment otf=new ResumeOTFragment();
        ft.replace(R.id.content_frame,otf,otf.getTag());
        ft.commit();
        saveButton.setVisibility(View.GONE);
    }
    public void executeSave(int selectMenu){
        switch (selectMenu){
            case -1:break;
            case 1: break;
            case 2: break;
            case 3: break;
            case 4: break;
            case 5: break;
            default: break;
        }
    }

    @Override
    public void onClick(View view) {
        solvingMenu(view.getId());
    }
    private void solvingMenu(int i){
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment;
        switch (i) {
            case R.id.fab_home:
                fragment=new ResumeOTFragment();//new ClientFragment();
                saveButton.setVisibility(View.GONE);
                break;

            case R.id.fab_info_cliente:
                saveButton.setVisibility(View.VISIBLE);
                fragment=new ClientFragment();
                break;
            case R.id.fab_info_vehiculo:
                saveButton.setVisibility(View.VISIBLE);
                fragment=new VehicleFragment();
                //      i.putExtra("goto", 2);
                break;
            case R.id.fab_inspeccion:
                saveButton.setVisibility(View.VISIBLE);
                fragment=new VehicleIntroDocFragment();
                break;
            case R.id.fab_trabajos_realizados:
                saveButton.setVisibility(View.VISIBLE);
                //      i.putExtra("goto", 3);
                fragment=new ListJobsFragment();
                break;
            case R.id.fab_repuestos:
                saveButton.setVisibility(View.VISIBLE);
                fragment=new RefillFragment();
                //      i.putExtra("goto", 4);
                break;
            case R.id.fab_cotizaciones:
                saveButton.setVisibility(View.VISIBLE);
                fragment=new BillListFragment();
                //       i.putExtra("goto", 5);
                break;
            default:
                saveButton.setVisibility(View.GONE);
                fragment=null;
                //        i.putExtra("goto", -1);
                break;
        }
        if(fragment!=null) {
            fam.close(true);
            ft.replace(R.id.content_frame, fragment, TAG);
            ft.commit();//      i.putExtra("goto", 1);

        }

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        this.finish();
        //super.onBackPressed();
    }
    public void handleSearchClientDialog(String dni){
        if(client_dialog==null){
            client_dialog=new Dialog(OrdenTrabajoMaintenanceActivity.this);
            client_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            client_dialog.setContentView(R.layout.dialog_search_client);
            client_dialog.setCancelable(true);
            client_dialog.create();
            client_dialog.show();
        }
    }
}

